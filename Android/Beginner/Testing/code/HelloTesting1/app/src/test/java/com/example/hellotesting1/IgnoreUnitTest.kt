package com.example.hellotesting1

import org.junit.Ignore
import org.junit.Test

class IgnoreUnitTest {

    @Ignore("Test is ignored")
    @Test
    fun retrieveInvalidIndex() {
        arrayListOf("haha")[3]
    }
}