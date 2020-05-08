package com.example.helloasynchronous1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val cryptocurrencyPrice = CryptocurrencyPrice(textView, progressBar)
        cryptocurrencyPrice.execute("Bitcoin")

        Log.d(LOG, "The execute method of the task is not blocking")
    }
}
