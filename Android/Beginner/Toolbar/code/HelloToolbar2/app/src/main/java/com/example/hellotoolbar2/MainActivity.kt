package com.example.hellotoolbar2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.apply {
            title = "Toolbar Title"
        }
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when {
            item.itemId == R.id.newAction -> {
                Log.d("menu", "newAction")
                true
            }
            item.itemId == R.id.downloadAction -> {
                Log.d("menu", "downloadAction")
                true
            }
            item.itemId == R.id.loginAction -> {
                Log.d("menu", "loginAction")
                true
            }
            else -> false
        }
    }
}
