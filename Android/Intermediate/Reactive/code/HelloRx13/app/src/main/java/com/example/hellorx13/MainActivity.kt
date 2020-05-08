package com.example.hellorx13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView
    private lateinit var button: Button
    private lateinit var editText: EditText
    private val compositeDisposable = CompositeDisposable()
    private var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editText)

        setupRxBinding()
    }

    fun setupRxBinding() {
        RxView
            .clicks(button)
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                i++
                textView.text = "Clicked $i times"
            }
            .addTo(compositeDisposable)

        RxTextView
            .textChangeEvents(editText)
            .debounce(500, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                textView.text = it.text().toString().toUpperCase(Locale.getDefault())
            }
            .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
