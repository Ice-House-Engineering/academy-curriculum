package com.example.helloanimation14

import android.animation.ObjectAnimator
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button : Button = findViewById(R.id.button)
        val path = Path().apply {
            cubicTo(0f, 0f, 500f, 0f, 0f, 500f)
        }
        ObjectAnimator.ofFloat(button, View.X, View.Y, path).apply {
            duration = 2000
            start()
        }

    }
}
