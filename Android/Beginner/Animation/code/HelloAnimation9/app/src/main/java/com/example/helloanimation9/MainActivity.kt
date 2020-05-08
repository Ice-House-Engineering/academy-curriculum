package com.example.helloanimation9

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Button
import android.widget.ImageView
import kotlin.math.hypot

// https://developer.android.com/training/animation/reveal-or-hide-view

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView: ImageView = findViewById(R.id.imageView)

        val buttonReveal: Button = findViewById(R.id.buttonReveal)
        buttonReveal.setOnClickListener {
            val cx = imageView.width / 2
            val cy = imageView.height / 2
            val radius = hypot(cx.toDouble(), cy.toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(imageView, cx, cy, 0f, radius)
            imageView.visibility = View.VISIBLE
            anim.start()
        }

        val buttonHide: Button = findViewById(R.id.buttonHide)
        buttonHide.setOnClickListener {
            val cx = imageView.width / 2
            val cy = imageView.height / 2
            val radius = hypot(cx.toDouble(), cy.toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(imageView, cx, cy, radius, 0f)

            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    imageView.visibility = View.INVISIBLE
                }
            })

            anim.start()
        }
    }
}
