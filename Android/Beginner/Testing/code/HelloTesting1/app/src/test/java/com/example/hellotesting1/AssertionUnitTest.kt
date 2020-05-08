package com.example.hellotesting1

import com.natpryce.hamkrest.allOf
import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import org.hamcrest.core.IsCollectionContaining.hasItems
import org.junit.*

import org.junit.Assert.*

class AssertionUnitTest {

    @Test
    fun assertThat_isCorrect() {
        assertEquals(16, 15 + 1)
        assertTrue("must be true", true)
        assertThat("check string", containsSubstring("check"))
        assertThat("check string", allOf(containsSubstring("check") and containsSubstring("string")))
        assertThat(arrayListOf("one", "two", "three"), hasItems("one", "three"))
    }
}