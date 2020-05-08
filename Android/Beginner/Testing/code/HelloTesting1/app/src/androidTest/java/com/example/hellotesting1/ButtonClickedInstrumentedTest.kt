package com.example.hellotesting1

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ButtonClickedInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun testButtonClicked() {
        val newText = "Hello Sun!"
        onView(withId(R.id.helloWorld)).check(matches(withText("Hello World!")))
        onView(withId(R.id.editText)).perform(clearText(), typeText(newText), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.helloWorld)).check(matches(withText(newText)))
    }
}