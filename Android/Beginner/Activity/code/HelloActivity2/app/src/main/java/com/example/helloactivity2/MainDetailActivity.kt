package com.example.helloactivity2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)

        val text = intent.getStringExtra("text")
        val textView = findViewById<TextView>(R.id.textView)
        if (!text.isNullOrEmpty()) {
            textView.text = text
        }
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("result", "Result")
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}