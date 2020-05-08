package com.example.hellodatapersistence4

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceManager


const val LOG = "android-sharedprefs"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Default shared preferences
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        with (prefs.edit()) {
            putInt("controlA", 3)
            putString("controlB", "Checked")
            commit()
        }

        val defaultValueInt = 0
        val defaultValueString = ""
        val controlA = prefs.getInt("controlA", defaultValueInt)
        val controlB = prefs.getString("controlB", defaultValueString)
        Log.d(LOG, controlA.toString())
        Log.d(LOG, controlB)

        // Shared preference with specific name
        val prefsByName = this.getSharedPreferences("preference1", Context.MODE_PRIVATE)
        with (prefsByName.edit()) {
            putLong("controlC", 2344L)
            apply()
        }

        val defaultValueLong = 0L
        val controlC = prefsByName.getLong("controlC", defaultValueLong)
        Log.d(LOG, controlC.toString())

        // Shared preference per activity
        val activityPrefs = this.getPreferences(Context.MODE_PRIVATE)
        with (activityPrefs.edit()) {
            val stringSet = setOf("bitcoin", "ethereum", "monero")
            putStringSet("controlD", stringSet)
            commit()
        }

        val controlD = activityPrefs.getStringSet("controlD", emptySet())
        Log.d(LOG, controlD.toString())

        // Callback
        prefs.registerOnSharedPreferenceChangeListener { p, s ->
            Log.d(LOG, "Value in preferences changed on: $s.")
            Log.d(LOG, "New value: ${p.getString(s, "")}")
        }

        with (prefs.edit()) {
            putString("controlB", "Unchecked")
            commit()
        }
    }
}
