package com.example.hellotoolbar1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.newAction -> {
                Log.d("Menu", "New Action")
                return true
            }
            R.id.downloadAction -> {
                Log.d("Menu", "Download Action")
                return true
            }
            R.id.loginAction -> {
                Log.d("Menu", "Login Action")
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
