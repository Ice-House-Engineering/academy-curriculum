package com.example.helloactivity1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainDeepActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_deep)
        val buttonLaunchActivity1 = findViewById<Button>(R.id.button_launch_activity_1)
        buttonLaunchActivity1.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        val buttonLaunchActivity1Only = findViewById<Button>(R.id.button_launch_activity_1_only)
        buttonLaunchActivity1Only.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
        val buttonPopToActivity1 = findViewById<Button>(R.id.pop_to_activity_1)
        buttonPopToActivity1.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
        title = "HelloActivity1 - Activity3"
    }
}