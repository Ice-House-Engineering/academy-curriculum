package com.example.hellorx15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo


const val LOG = "rxbinding-"

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private val compositeDisposable = CompositeDisposable()
    val cryptocurrencies = arrayOf("Bitcoin", "Monero", "Ethereum", "Bitcoin Cash", "Litecoin", "EOS", "Ada", "BnB")

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

        (viewAdapter as CryptoAdapter).subject.subscribe {
            Log.d(LOG, it.toString())
        }.addTo(compositeDisposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}
