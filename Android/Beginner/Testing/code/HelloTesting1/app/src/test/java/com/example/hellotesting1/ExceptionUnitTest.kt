package com.example.hellotesting1

import org.junit.Test

class ExceptionUnitTest {

    @Test(expected=IndexOutOfBoundsException::class)
    fun retrieveInvalidIndex() {
        arrayListOf("haha")[3]
    }
}