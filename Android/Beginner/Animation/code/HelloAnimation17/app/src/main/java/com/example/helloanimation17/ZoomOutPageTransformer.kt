package com.example.helloanimation17

import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.max
import kotlin.math.abs

private const val MIN_SCALE = 0.85f
private const val MIN_ALPHA = 0.5f

class ZoomOutPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        view.apply {
            val scaleFactor = max(MIN_SCALE, 1 - abs(position))

            scaleX = scaleFactor
            scaleY = scaleFactor

            alpha = (MIN_ALPHA +
                    (((scaleFactor - MIN_SCALE) / (1 - MIN_SCALE)) * (1 - MIN_ALPHA)))

        }
    }
}
