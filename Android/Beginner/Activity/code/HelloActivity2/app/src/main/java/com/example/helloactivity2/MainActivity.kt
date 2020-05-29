package com.example.helloactivity2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val request_code = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button_launch_activity_2)
        button.setOnClickListener {
            val intent = Intent(this, MainDetailActivity::class.java)
            intent.putExtra("text", "Beautiful World")
            startActivity(intent)
        }
        val button2 = findViewById<Button>(R.id.button_launch_activity_2_with_result)
        button2.setOnClickListener {
            val intent = Intent(this, MainDetailActivity::class.java)
            startActivityForResult(intent, request_code)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode== Activity.RESULT_OK) {
            if (requestCode==request_code) {
                val result = data!!.getStringExtra("result")
                val textView = findViewById<TextView>(R.id.textViewResult)
                textView.text = result
            }
        }
    }
}
