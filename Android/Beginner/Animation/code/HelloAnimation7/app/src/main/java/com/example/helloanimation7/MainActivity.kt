package com.example.helloanimation7

import android.animation.AnimatorInflater
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val animationOnButton = AnimationUtils.loadAnimation(this, R.anim.basic_animation)
        button.startAnimation(animationOnButton)

        val button2 = findViewById<Button>(R.id.button2)
        AnimatorInflater.loadAnimator(this, R.animator.basic_animator).apply {
            setTarget(button2)
            start()
        }
    }
}
