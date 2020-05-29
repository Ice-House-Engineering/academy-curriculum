package com.example.helloconcurrency5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.*


const val LOG = "android-coroutine"

class DetailActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            launch {
                repeat(10) {
                    Log.d(LOG, "Inside coroutine printing $it...")
                    delay(1000)
                }
            }
        }
    }

    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }
}