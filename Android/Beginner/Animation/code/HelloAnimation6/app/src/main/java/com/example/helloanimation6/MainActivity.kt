package com.example.helloanimation6

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        val valueAnimator1 = ValueAnimator.ofFloat(0f, 500f)
        valueAnimator1.addUpdateListener {
            val value = it.animatedValue as Float
            button.translationY = value
        }

        valueAnimator1.interpolator = LinearInterpolator()
        valueAnimator1.duration = 2000

        val valueAnimator2 = ValueAnimator.ofFloat(0f, 360f)
        valueAnimator2.addUpdateListener {
            val value = it.animatedValue as Float
            button.rotation = value
        }

        valueAnimator2.interpolator = LinearInterpolator()
        valueAnimator2.duration = 2000

        val objectAnimator = ObjectAnimator.ofFloat(button, "scaleX", 1f, 3f)
        objectAnimator.duration = 3000

        AnimatorSet().apply {
            play(valueAnimator1).with(objectAnimator).before(valueAnimator2)
            start()
        }
    }
}
