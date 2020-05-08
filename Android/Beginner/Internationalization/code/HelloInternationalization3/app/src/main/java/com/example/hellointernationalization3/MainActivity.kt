package com.example.hellointernationalization3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

const val LOG = "date-formatting"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val date = Date()
        Log.d(LOG, date.toString())

        val mediumdf = DateFormat.getDateInstance(DateFormat.MEDIUM)
        val mediumdateString = mediumdf.format(date)
        Log.d(LOG, mediumdateString)

        val shortdf = DateFormat.getDateInstance(DateFormat.SHORT)
        val shortdateString = shortdf.format(date)
        Log.d(LOG, shortdateString)
    }
}
