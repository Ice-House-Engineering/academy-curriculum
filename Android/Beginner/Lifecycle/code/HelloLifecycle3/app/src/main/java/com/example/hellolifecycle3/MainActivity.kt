package com.example.hellolifecycle3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val tag = "activity-lifecycle"
    private var strings = arrayOf("bitcoin", "ethereum", "bitcoin cash", "monero", "eos")
    private var selectedRow = ""
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)
        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, position: Int, id: Long ->
            selectedRow = strings[position]
            Log.d("item", selectedRow)

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("text", selectedRow)
            startActivity(intent)
        }

        Log.d(tag, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(tag, "onStart")
    }

    override fun onResume() {
        super.onResume()

        // Simulating the deletion of item
        strings = strings.filterNot { it == selectedRow }.toTypedArray()
        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, strings)
        listView.adapter = adapter

        Log.d(tag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(tag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(tag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(tag, "onDestroy")
    }
}