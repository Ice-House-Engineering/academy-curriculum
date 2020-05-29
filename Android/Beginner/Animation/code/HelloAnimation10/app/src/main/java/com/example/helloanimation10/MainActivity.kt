package com.example.helloanimation10

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        val kf0 = Keyframe.ofFloat(0f, 0f)
        val kf1 = Keyframe.ofFloat(.5f, 360f)
        val kf2 = Keyframe.ofFloat(.99f, 180f)
        val kf3 = Keyframe.ofFloat(1f, 0f)

        val pvhRotation = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2, kf3)
        ObjectAnimator.ofPropertyValuesHolder(button, pvhRotation).apply {
            duration = 5000
            start()
        }
    }
}
