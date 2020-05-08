package com.example.hellotesting2

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecyclerviewInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    companion object {
        @BeforeClass
        @JvmStatic
        fun setDevicePreferences() {
            val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
            uiDevice.executeShellCommand("settings put global animator_duration_scale 0.0")
            uiDevice.executeShellCommand("settings put global transition_animation_scale 0.0")
            uiDevice.executeShellCommand("settings put global window_animation_scale 0.0")
        }
    }

    @Test
    fun testRowClicked() {
        onView(withId(R.id.recyclerView)).perform(actionOnItemAtPosition<CryptoViewHolder>(2, click()))
        onView(withText("Ethereum")).check(matches(isDisplayed()))
    }
}