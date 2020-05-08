package com.example.helloadapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    val strings = arrayOf("bitcoin", "ethereum", "bitcoin cash", "monero", "eos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)
        listView.adapter = adapter

        listView.setOnItemClickListener { adapterView, view, position: Int, id: Long ->
            Log.d("item", strings[position])
        }
    }
}
