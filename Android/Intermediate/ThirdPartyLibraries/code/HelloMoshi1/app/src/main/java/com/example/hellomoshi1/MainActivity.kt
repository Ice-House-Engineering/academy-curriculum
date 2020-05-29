package com.example.hellomoshi1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import java.util.*


const val TAG = "hello-moshi"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Converting from object to json
        val forkBitcoinCash = Fork("Bitcoin Cash", 220)
        val forkBitcoinSV = Fork("Bitcoin SV", 79)
        val currentDate = Date()
        val cryptocurrency = Cryptocurrency("Bitcoin", 6633, 121313, listOf(forkBitcoinCash, forkBitcoinSV), currentDate)

        val moshi = Moshi.Builder().add(currentDate.javaClass, Rfc3339DateJsonAdapter()).build()
        val adapter = moshi.adapter(cryptocurrency.javaClass)
        val jsonResult = adapter.toJson(cryptocurrency)

        Log.d(TAG, jsonResult)

        // Converting from json to object
        val cryptocurrency2 = adapter.fromJson(jsonResult)

        Log.d(TAG, cryptocurrency2.toString())

        // Converting from object to json with custom adapter
        val customMoshi = Moshi.Builder().add(CryptocurrencyAdapter()).build()
        val customAdapter = customMoshi.adapter(cryptocurrency.javaClass)
        val customJsonResult = customAdapter.toJson(cryptocurrency)

        Log.d(TAG, customJsonResult)

        // Converting from json to object with custom adapter
        val cryptocurrencyWithCustomAdapter = customAdapter.fromJson(customJsonResult)

        Log.d(TAG, cryptocurrencyWithCustomAdapter.toString())
    }
}
