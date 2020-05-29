package com.example.hellomvvm4.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.hellomvvm4.R
import com.example.hellomvvm4.viewmodel.BasicAndroidViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var button: Button
    private val viewModel: BasicAndroidViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        val cryptocurrencyObserver = Observer<String> {
            textView.text = it
        }
        viewModel.getCryptocurrencies().observe(this, cryptocurrencyObserver)

        button.setOnClickListener {
            val newCryptocurrency = "Ethereum"
            viewModel.setCryptocurrencies(newCryptocurrency)
        }
    }

}
