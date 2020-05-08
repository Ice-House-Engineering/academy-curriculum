package com.example.hellotoolbar3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        toolbar.title = "Hello Collapsing Toolbar"

        setSupportActionBar(toolbar)
    }
}
