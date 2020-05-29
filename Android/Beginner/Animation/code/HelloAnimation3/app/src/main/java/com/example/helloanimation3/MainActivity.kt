package com.example.helloanimation3

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonScaleX = findViewById<Button>(R.id.buttonScaleX)
        val buttonScaleY = findViewById<Button>(R.id.buttonScaleY)
        val buttonAlpha = findViewById<Button>(R.id.buttonAlpha)

        val valueAnimator = ValueAnimator.ofFloat(0f, 5f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float

            buttonScaleX.scaleX = value
            buttonScaleY.scaleY = value
            buttonAlpha.alpha = value / 5
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 5000

        valueAnimator.start()
    }
}
