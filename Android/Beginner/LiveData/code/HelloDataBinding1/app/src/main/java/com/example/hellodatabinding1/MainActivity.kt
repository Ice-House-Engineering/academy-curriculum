package com.example.hellodatabinding1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.hellodatabinding1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myData = MyData("jakarta", "indonesia", 56)
        binding.activity = this
    }

    fun onButtonClicked(view: View) {
        Log.d("button-click", "Button is clicked")
    }
}
