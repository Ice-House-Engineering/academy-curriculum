package com.example.hellotesting1

import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.rules.TemporaryFolder


class RulesUnitTest {
    @get:Rule
    val tempFolder : TemporaryFolder = TemporaryFolder()

    @get:Rule
    val exception = ExpectedException.none()

    @Test
    fun checkFileExists() {
        val testFile = tempFolder.newFile("test.txt")
        assertTrue(testFile.exists())
    }

    @Test
    fun accessInvalidIndex() {
        exception.expect(IndexOutOfBoundsException::class.java)
        exception.expectMessage("Index: 3, Size: 1")
        arrayListOf("haha")[3]
    }
}