package com.example.hellotesting1

import org.junit.Test
import java.util.concurrent.TimeUnit

class TimeoutUnitTest {

    @Test(timeout=1100)
    fun veryLongUnitTest() {
        TimeUnit.SECONDS.sleep(1)
    }
}