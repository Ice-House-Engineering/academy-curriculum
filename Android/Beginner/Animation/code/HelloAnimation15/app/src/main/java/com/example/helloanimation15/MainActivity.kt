package com.example.helloanimation15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val springAnim1 = button.let {
                SpringAnimation(it, DynamicAnimation.TRANSLATION_Y, 600f).apply {
                    spring.stiffness = SpringForce.STIFFNESS_LOW

                    val pixelPerSecond: Float =
                        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10000f, resources.displayMetrics)
                    setStartVelocity(pixelPerSecond)

                }
            }
            springAnim1.start()

            val springAnim2 = button.let {
                SpringAnimation(it, DynamicAnimation.SCALE_X, 2f).apply {
                    spring.dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                }
            }
            springAnim2.start()
        }
    }
}
