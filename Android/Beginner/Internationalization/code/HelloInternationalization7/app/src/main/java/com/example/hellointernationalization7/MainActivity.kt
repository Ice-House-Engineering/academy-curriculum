package com.example.hellointernationalization7

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

const val LOG = "locale-settings"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val locales = resources.configuration.locales
        val locale = locales[0]
        Log.d(LOG, locale.toString())
        if (locales.size()>1) {
            val locale2 = locales[1]
            Log.d(LOG, locale2.toString())
        }

        Log.d(LOG, locale.country)
        Log.d(LOG, locale.language)
        Log.d(LOG, locale.displayName)
        Log.d(LOG, locale.displayCountry)
        Log.d(LOG, locale.displayLanguage)

    }

    override fun attachBaseContext(newBase: Context?) {
        // val context = ContextWrapper.wrap(newBase!!, Locale("in", "ID"))
        val context = ContextWrapper.wrap(newBase!!, Locale("en", "US"))
        super.attachBaseContext(context)
    }
}
