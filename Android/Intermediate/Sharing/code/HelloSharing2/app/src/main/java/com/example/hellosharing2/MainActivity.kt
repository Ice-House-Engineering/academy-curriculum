package com.example.hellosharing2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


// Adapted from https://developer.android.com/training/sharing/receive
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        when {
            intent?.action == Intent.ACTION_SEND -> {
                if ("text/plain" == intent.type) {
                    findViewById<TextView>(R.id.textView).text = intent.getStringExtra(Intent.EXTRA_TEXT)
                }
            }
        }
    }
}
