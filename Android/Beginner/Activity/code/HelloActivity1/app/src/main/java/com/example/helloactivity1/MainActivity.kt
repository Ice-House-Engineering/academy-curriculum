package com.example.helloactivity1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button_launch_activity_2)
        button.setOnClickListener {
            startActivity(Intent(this, MainDetailActivity::class.java))
        }
        title = "HelloActivity1 - Activity 1"
    }
}
