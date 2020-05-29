package com.example.hellointernationalization4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

const val LOG = "i18n"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val helloWorld : String = getString(R.string.hello_world)
        Log.d(LOG, helloWorld)

        val incomingTransfer : String = getString(R.string.incoming_transfer, "Sarah Connor", 3000)
        Log.d(LOG, incomingTransfer)

        val oneBook = 1
        val oneBookString = resources.getQuantityString(R.plurals.books, oneBook, oneBook)
        Log.d(LOG, oneBookString)

        val fiveBooks = 5
        val fiveBooksString = resources.getQuantityString(R.plurals.books, fiveBooks, fiveBooks)
        Log.d(LOG, fiveBooksString)
    }
}
