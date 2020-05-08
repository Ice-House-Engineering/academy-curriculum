package com.example.hellotesting3

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ListviewInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRowClicked() {
        onData(instanceOf(String::class.java))
            .inAdapterView(withId(R.id.listView))
            .atPosition(2)
            .check(matches(withText("bitcoin cash")))
    }
}