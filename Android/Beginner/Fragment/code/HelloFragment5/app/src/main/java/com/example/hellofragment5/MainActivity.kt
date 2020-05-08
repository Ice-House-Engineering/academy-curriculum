package com.example.hellofragment5

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.transaction {
                add(android.R.id.content, ListFragment())
            }
        }
    }
}
