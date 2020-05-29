package com.example.hellotesting1

import org.junit.*
import org.junit.Assert.assertEquals


class ExampleUnitTest {

    companion object {
        init {
            println("init")
        }

        val variable1 = 7

        @BeforeClass
        @JvmStatic
        fun setup() {
            println("BeforeClass")
        }

        @AfterClass
        @JvmStatic
        fun teardown() {
            println("AfterClass")
        }
    }

    val variable2 = 9

    @Before
    fun beforeEachTest() {
        println("Before each test")
    }

    @After
    fun afterEachTest() {
        println("After each test")
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(16, variable2 + variable1)
    }

    @Test
    fun subtraction_isCorrect() {
        assertEquals(2, variable2 - variable1)
    }
}
