package com.example.hellotesting1

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized


@RunWith(Parameterized::class)
class ParameterizedUnitTest(val fInput: Int, val fExpected: Int) {

    companion object {
        @Parameterized.Parameters
        @JvmStatic fun data() =
            arrayListOf(
                arrayOf(1, 2),
                arrayOf(2, 4),
                arrayOf(10, 20)
            )
    }

    @Test
    fun twoTimes_isCorrect() {
        Assert.assertEquals(fExpected, 2 * fInput)
    }
}