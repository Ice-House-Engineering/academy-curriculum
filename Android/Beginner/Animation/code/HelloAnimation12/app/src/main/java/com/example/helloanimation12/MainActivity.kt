package com.example.helloanimation12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout: LinearLayout = findViewById(R.id.linearLayout)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val textView = TextView(this)
            textView.text = "Hello World"

            linearLayout.addView(textView, 0)
        }
    }
}
