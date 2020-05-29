package com.example.helloanimation1

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.BounceInterpolator
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        val valueAnimator = ValueAnimator.ofFloat(0f, 1000f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            button.translationX = value / 3
            button.translationY = value
        }

        valueAnimator.interpolator = BounceInterpolator()
        valueAnimator.duration = 2000

        valueAnimator.start()
    }
}
