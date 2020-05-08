package com.example.helloanimation16

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val flingAnim = button.let {
                FlingAnimation(it, DynamicAnimation.ROTATION).apply {
                    val pixelPerSecond: Float =
                        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5000f, resources.displayMetrics)
                    setStartVelocity(pixelPerSecond)
                    friction = 2.1f
                }
            }
            flingAnim.start()
        }
    }
}
