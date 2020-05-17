package com.airbnb.mvrx

import kotlin.reflect.KProperty1

class CopyHelper<T : Any>(clazz: Class<T>) {

    private val nameToIndex = mutableMapOf<String, Int>()
    private val copyDefault = clazz.methods.first { it.name == "copy\$default" }

    private val maskCount: Int = (copyDefault.parameterTypes.size - 2 + 32) / 33
    private val parametersCount = copyDefault.parameterTypes.size - 2 - maskCount

    private val parametersOffset = 1
    private val masksOffset = parametersOffset + parametersCount

    private val copyDefaultParams = run {
        val params = arrayOfNulls<Any>(2 + parametersCount + maskCount)
        copyDefault.parameterTypes.drop(1).take(parametersCount)
            .forEachIndexed { index, parameter ->
                params[parametersOffset + index] = when (parameter) {
                    Boolean::class.java -> false
                    Char::class.java -> 0.toChar()
                    Byte::class.java -> 0.toByte()
                    Short::class.java -> 0.toShort()
                    Int::class.java -> 0
                    Float::class.java -> 0f
                    Long::class.java -> 0L
                    Double::class.java -> 0.0
                    else -> null
                }
            }
        for (i in 0 until maskCount) {
            params[masksOffset + i] = -1
        }

        params
    }

    fun <R : Any?> copy(receiver: T, prop: KProperty1<T, R>, value: R): T {
        val currentValue = prop.get(receiver)
        if (currentValue == value) {
            return receiver
        }

        val propertyIndex = nameToIndex[prop.name]

        if (propertyIndex == null) {
            for (i in 0 until parametersCount) {
                val result = kotlin.runCatching { copy(receiver, i, value) }
                result.onSuccess { newValue ->
                    if (prop.get(newValue) == value) {
                        nameToIndex[prop.name] = i
                        return newValue
                    }
                }
            }
            error("Can't find property in constructor")
        }

        return copy(receiver, propertyIndex, value)
    }

    fun copy(receiver: T, propertyIndex: Int, value: Any?): T {
        val maskIndex = propertyIndex / 32
        val indexInMask = propertyIndex % 32

        val params = copyDefaultParams

        val defaultValue = params[parametersOffset + propertyIndex]

        params[0] = receiver
        params[parametersOffset + propertyIndex] = value
        params[masksOffset + maskIndex] = (1 shl indexInMask).inv()
        params[params.size - 1] = receiver

        val result = kotlin.runCatching { copyDefault.invoke(null, *params) as T }

        params[0] = null
        params[parametersOffset + propertyIndex] = defaultValue
        params[masksOffset + maskIndex] = -1

        return result.getOrThrow()
    }
}