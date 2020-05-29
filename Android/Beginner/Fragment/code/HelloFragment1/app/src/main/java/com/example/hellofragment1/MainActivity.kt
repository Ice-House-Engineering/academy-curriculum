package com.example.hellofragment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonCallback() {
        Toast.makeText(this, "Button is clicked", Toast.LENGTH_SHORT).show()
    }
}
