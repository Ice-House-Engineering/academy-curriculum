package com.example.hellomvvm3.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.hellomvvm3.R
import com.example.hellomvvm3.databinding.ActivityMainBinding
import com.example.hellomvvm3.viewmodel.BasicViewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: BasicViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }
}
