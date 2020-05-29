package com.example.hellosharing1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


// Adapted from https://developer.android.com/training/sharing/send
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "Hello Jakarta")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "This is the title")
            startActivity(shareIntent)
        }
    }
}
