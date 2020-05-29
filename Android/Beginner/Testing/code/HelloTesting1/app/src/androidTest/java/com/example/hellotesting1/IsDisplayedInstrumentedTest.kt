package com.example.hellotesting1

import android.widget.TextView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule
import org.junit.Assert.assertEquals


@RunWith(AndroidJUnit4::class)
class IsDisplayedInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testHelloWorld() {
        onView(withText("Hello World!")).check(matches(isDisplayed()))
        assertEquals(activityRule.activity.findViewById<TextView>(R.id.helloWorld).text, "Hello World!")
    }
}