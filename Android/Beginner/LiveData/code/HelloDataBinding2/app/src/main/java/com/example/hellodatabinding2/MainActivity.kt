package com.example.hellodatabinding2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.hellodatabinding2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding =  DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        binding.myPicture = R.drawable.tree
        binding.activity = this
    }

    fun onButtonClicked(view: View) {
        binding.myPicture = R.drawable.dogrobot
    }
}