package com.example.helloanimation2

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonRotateX = findViewById<Button>(R.id.buttonRotateX)
        val buttonRotateY = findViewById<Button>(R.id.buttonRotateY)
        val buttonRotate = findViewById<Button>(R.id.buttonRotate)

        val valueAnimator = ValueAnimator.ofFloat(0f, 360f)
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Float

            buttonRotateX.rotationX = value
            buttonRotateY.rotationY = value
            buttonRotate.rotation = value
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 5000

        valueAnimator.start()
    }
}
