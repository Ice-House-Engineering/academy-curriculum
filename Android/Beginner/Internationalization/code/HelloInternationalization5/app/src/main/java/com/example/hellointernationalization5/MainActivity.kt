package com.example.hellointernationalization5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.text.DateFormat
import java.text.NumberFormat
import java.util.*

const val LOG = "date-formatting"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val date = Date()

        val mediumDf : DateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
        val mediumDateString = mediumDf.format(date)
        Log.d(LOG, mediumDateString)

        val shortDf : DateFormat = DateFormat.getDateInstance(DateFormat.SHORT)
        val shortDateString = shortDf.format(date)
        Log.d(LOG, shortDateString)

        val number = 12345678.34

        val nf = NumberFormat.getNumberInstance()
        val numberString = nf.format(number)
        Log.d(LOG, numberString)

        val cf = NumberFormat.getCurrencyInstance()
        val currencyString = cf.format(number)
        Log.d(LOG, currencyString)

    }
}
