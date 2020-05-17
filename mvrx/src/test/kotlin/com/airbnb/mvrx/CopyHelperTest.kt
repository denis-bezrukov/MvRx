package com.airbnb.mvrx

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.concurrent.ThreadLocalRandom

//fun main() {
//    val propertiesCount = 65
//
//    val generatedClass = (0 until propertiesCount).joinToString("\n", prefix = "data class LargeClass$propertiesCount(\n", postfix = "\n)") { line ->
//        buildString {
//            append("    val prop$line: Int = $line")
//            if (line < propertiesCount - 1) append(',')
//        }
//    }
//    val properties = (0 until propertiesCount).joinToString("\n", prefix = "val properties = listOf(\n", postfix = "\n)") { line ->
//        buildString {
//            append("    LargeClass$propertiesCount::prop$line")
//            if (line < propertiesCount - 1) append(',')
//        }
//    }
//
//    println(generatedClass)
//    println(properties)
//}

class CopyHelperTest {

    data class Simple(val a: Int = 5)

    data class Three(val a: Int, val b: Int, val c: Int)

    @Test
    fun simpleTest() {
        val data = Simple(1)
        val helper = CopyHelper(Simple::class.java)
        assertEquals(Simple(6), helper.copy(data, Simple::a, 6))
    }

    @Test
    fun threePropsTest() {
        val d1 = Three(1, 2, 3)
        val helper = CopyHelper(Three::class.java)

        val d2 = helper.copy(d1, Three::c, 100)
        assertEquals(d1.copy(c = 100), d2)

        val d3 = helper.copy(d2, Three::b, 101)
        assertEquals(d2.copy(b = 101), d3)

        val d4 = helper.copy(d3, Three::a, 102)
        assertEquals(d3.copy(a = 102), d4)
    }

    data class LargeClass65(
            val prop0: Int = 0,
            val prop1: Int = 1,
            val prop2: Int = 2,
            val prop3: Int = 3,
            val prop4: Int = 4,
            val prop5: Int = 5,
            val prop6: Int = 6,
            val prop7: Int = 7,
            val prop8: Int = 8,
            val prop9: Int = 9,
            val prop10: Int = 10,
            val prop11: Int = 11,
            val prop12: Int = 12,
            val prop13: Int = 13,
            val prop14: Int = 14,
            val prop15: Int = 15,
            val prop16: Int = 16,
            val prop17: Int = 17,
            val prop18: Int = 18,
            val prop19: Int = 19,
            val prop20: Int = 20,
            val prop21: Int = 21,
            val prop22: Int = 22,
            val prop23: Int = 23,
            val prop24: Int = 24,
            val prop25: Int = 25,
            val prop26: Int = 26,
            val prop27: Int = 27,
            val prop28: Int = 28,
            val prop29: Int = 29,
            val prop30: Int = 30,
            val prop31: Int = 31,
            val prop32: Int = 32,
            val prop33: Int = 33,
            val prop34: Int = 34,
            val prop35: Int = 35,
            val prop36: Int = 36,
            val prop37: Int = 37,
            val prop38: Int = 38,
            val prop39: Int = 39,
            val prop40: Int = 40,
            val prop41: Int = 41,
            val prop42: Int = 42,
            val prop43: Int = 43,
            val prop44: Int = 44,
            val prop45: Int = 45,
            val prop46: Int = 46,
            val prop47: Int = 47,
            val prop48: Int = 48,
            val prop49: Int = 49,
            val prop50: Int = 50,
            val prop51: Int = 51,
            val prop52: Int = 52,
            val prop53: Int = 53,
            val prop54: Int = 54,
            val prop55: Int = 55,
            val prop56: Int = 56,
            val prop57: Int = 57,
            val prop58: Int = 58,
            val prop59: Int = 59,
            val prop60: Int = 60,
            val prop61: Int = 61,
            val prop62: Int = 62,
            val prop63: Int = 63,
            val prop64: Int = 64
    )

    @Test
    fun testLarge() {
        val properties = listOf(
                LargeClass65::prop0,
                LargeClass65::prop1,
                LargeClass65::prop2,
                LargeClass65::prop3,
                LargeClass65::prop4,
                LargeClass65::prop5,
                LargeClass65::prop6,
                LargeClass65::prop7,
                LargeClass65::prop8,
                LargeClass65::prop9,
                LargeClass65::prop10,
                LargeClass65::prop11,
                LargeClass65::prop12,
                LargeClass65::prop13,
                LargeClass65::prop14,
                LargeClass65::prop15,
                LargeClass65::prop16,
                LargeClass65::prop17,
                LargeClass65::prop18,
                LargeClass65::prop19,
                LargeClass65::prop20,
                LargeClass65::prop21,
                LargeClass65::prop22,
                LargeClass65::prop23,
                LargeClass65::prop24,
                LargeClass65::prop25,
                LargeClass65::prop26,
                LargeClass65::prop27,
                LargeClass65::prop28,
                LargeClass65::prop29,
                LargeClass65::prop30,
                LargeClass65::prop31,
                LargeClass65::prop32,
                LargeClass65::prop33,
                LargeClass65::prop34,
                LargeClass65::prop35,
                LargeClass65::prop36,
                LargeClass65::prop37,
                LargeClass65::prop38,
                LargeClass65::prop39,
                LargeClass65::prop40,
                LargeClass65::prop41,
                LargeClass65::prop42,
                LargeClass65::prop43,
                LargeClass65::prop44,
                LargeClass65::prop45,
                LargeClass65::prop46,
                LargeClass65::prop47,
                LargeClass65::prop48,
                LargeClass65::prop49,
                LargeClass65::prop50,
                LargeClass65::prop51,
                LargeClass65::prop52,
                LargeClass65::prop53,
                LargeClass65::prop54,
                LargeClass65::prop55,
                LargeClass65::prop56,
                LargeClass65::prop57,
                LargeClass65::prop58,
                LargeClass65::prop59,
                LargeClass65::prop60,
                LargeClass65::prop61,
                LargeClass65::prop62,
                LargeClass65::prop63,
                LargeClass65::prop64
        )
        var data = LargeClass65()

        val helper = CopyHelper(LargeClass65::class.java)
        repeat(10) {
            (properties.indices).shuffled().forEach {
                val value = ThreadLocalRandom.current().nextInt()
                val newData = helper.copy(data, properties[it], value)
                assertEquals(properties[it].get(newData), value)
                for (p in properties) {
                    if (p != properties[it]) {
                        assertEquals(p.get(data), p.get(newData))
                    }
                }
                data = newData
            }
        }

    }
}