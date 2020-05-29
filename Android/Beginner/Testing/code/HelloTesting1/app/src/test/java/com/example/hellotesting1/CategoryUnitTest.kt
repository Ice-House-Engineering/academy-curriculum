package com.example.hellotesting1

import org.junit.Assert
import org.junit.Test
import org.junit.experimental.categories.Category


@Category(FastTests::class)
class CategoryUnitTest {

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(16, 15 + 1)
    }
}

@Category(SlowTests::class)
class Category2UnitTest {

    @Test
    fun addition_isCorrect() {
        Assert.assertEquals(16, 15 + 2)
    }
}