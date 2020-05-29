package com.example.helloactivity1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_detail)

        val buttonLaunchActivity3 = findViewById<Button>(R.id.button_launch_activity_3)
        buttonLaunchActivity3.setOnClickListener {
            startActivity(Intent(this, MainDeepActivity::class.java))
        }
        val buttonBackToActivity1 = findViewById<Button>(R.id.back_to_activity_1)
        buttonBackToActivity1.setOnClickListener {
            finish()
        }
        val buttonBrowser = findViewById<Button>(R.id.launch_browser)
        buttonBrowser.setOnClickListener {
            //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://liputan6.com")))
            startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:123")))
        }
        title = "HelloActivity1 - Activity 2"
    }
}