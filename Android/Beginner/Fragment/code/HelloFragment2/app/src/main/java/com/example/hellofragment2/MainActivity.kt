package com.example.hellofragment2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (supportFragmentManager.findFragmentById(android.R.id.content) == null) {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content,
                    ButtonFragment()).commit()
        }
    }

    fun buttonCallback() {
        Toast.makeText(this, "Button is clicked", Toast.LENGTH_SHORT).show()
    }
}
