package com.example.helloanimation4

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container = findViewById<ConstraintLayout>(R.id.container)

        val valueAnimator = ValueAnimator.ofInt(ContextCompat.getColor(this, R.color.colorPrimaryDark), ContextCompat.getColor(this, R.color.colorAccent))
        valueAnimator.addUpdateListener {
            val value = it.animatedValue as Int

            container.setBackgroundColor(value)
        }

        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 5000
        valueAnimator.setEvaluator(ArgbEvaluator())
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.repeatMode = ValueAnimator.REVERSE

        valueAnimator.start()
    }
}
