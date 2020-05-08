package com.example.hellotesting2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    val cryptocurrencies = arrayOf("Bitcoin", "Monero", "Ethereum", "Bitcoin Cash", "Litecoin", "EOS", "Ada", "BnB",
        "ZCash", "Tron", "BSV", "Stellar", "Cardano", "Tether", "IOTA", "Dash", "Chainlink")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewManager = LinearLayoutManager(this)
        viewAdapter = CryptoAdapter(cryptocurrencies)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
