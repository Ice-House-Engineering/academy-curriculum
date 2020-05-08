package com.example.hellointernationalization6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

const val LOG = "date-formatting"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val date = LocalDate.now()

        val mediumDateString = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))
        Log.d(LOG, mediumDateString)
        val shortDateString = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
        Log.d(LOG, shortDateString)

    }
}
