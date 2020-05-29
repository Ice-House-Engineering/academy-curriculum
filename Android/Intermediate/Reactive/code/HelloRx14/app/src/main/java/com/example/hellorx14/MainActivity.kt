package com.example.hellorx14

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import com.jakewharton.rxbinding2.widget.RxAdapterView
import com.jakewharton.rxbinding2.widget.RxSeekBar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo


class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner
    private lateinit var textView: TextView
    private lateinit var seekBar: SeekBar
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        textView = findViewById(R.id.textView)
        seekBar = findViewById(R.id.seekBar)

        setupSpinner()
        setupRxBinding()
    }

    fun setupSpinner() {
        ArrayAdapter.createFromResource(this, R.array.cryptocurrencies, android.R.layout.simple_spinner_item).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    fun setupRxBinding() {
        RxAdapterView.itemSelections(spinner).subscribeOn(AndroidSchedulers.mainThread()).subscribe {
            textView.text = resources.getStringArray(R.array.cryptocurrencies)[it]
        }.addTo(compositeDisposable)
        RxSeekBar.changes(seekBar).subscribeOn(AndroidSchedulers.mainThread()).subscribe {
            textView.text = "$it% from SeekBar"
        }.addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
