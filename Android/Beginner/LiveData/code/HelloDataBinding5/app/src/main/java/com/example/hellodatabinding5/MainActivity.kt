package com.example.hellodatabinding5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders


class MainActivity : AppCompatActivity() {

    private lateinit var model: MyCryptocurrencyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        model = ViewModelProviders.of(this).get(MyCryptocurrencyViewModel::class.java)

        val textView : TextView = findViewById(R.id.textView)
        val textView2 : TextView = findViewById(R.id.textView2)

        val nameObserver = Observer<String> { newName ->
            textView.text = newName
        }

        val rankObserver = Observer<String> { newRank ->
            textView2.text = newRank
        }

        findViewById<Button>(R.id.button).setOnClickListener {
            model.name.setValue("New Name")
        }

        model.fullname.observe(this, nameObserver)
        model.rank.observe(this, rankObserver)
    }
}
