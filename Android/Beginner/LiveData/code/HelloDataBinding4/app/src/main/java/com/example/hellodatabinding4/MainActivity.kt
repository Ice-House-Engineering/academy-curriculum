package com.example.hellodatabinding4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.hellodatabinding4.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding =  DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.myText = "Initial Data"
        binding.activity = this
    }

    fun onButtonClicked(view: View) {
        Log.d("button-clicked", binding.myText)
    }
}
