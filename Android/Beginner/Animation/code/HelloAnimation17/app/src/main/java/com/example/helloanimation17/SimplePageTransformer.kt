package com.example.helloanimation17

import android.view.View
import androidx.viewpager2.widget.ViewPager2


class SimplePageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            when {
                position < 0 -> {
                    alpha = 0.5f
                    scaleX = 0.5f
                    scaleY = 0.5f
                    rotation = 0f
                }
                position <= 0.01f -> {
                    alpha = 1f
                    scaleX = 1f
                    scaleY = 1f
                    rotation = 0f
                }
                position <= 1 -> {
                    alpha = 1f
                    scaleX = 1f
                    scaleY = 1f
                    rotation = 45f
                }
            }
        }
    }
}