package com.example.helloanimation5

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        val objectAnimator = ObjectAnimator.ofFloat(button, "translationX", 0f, 500f)
        objectAnimator.duration = 3000
        objectAnimator.start()
    }
}
