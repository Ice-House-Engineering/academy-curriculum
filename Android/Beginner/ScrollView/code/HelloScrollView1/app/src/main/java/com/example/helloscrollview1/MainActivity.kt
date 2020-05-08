package com.example.helloscrollview1

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scrollView : ScrollView = findViewById(R.id.scrollView)
        findViewById<Button>(R.id.buttonScrollToXY).setOnClickListener {
            scrollView.scrollTo(0, 1600)
        }
        findViewById<Button>(R.id.buttonFullScroll).setOnClickListener {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }

        if (Build.VERSION.SDK_INT >= 29) {
            findViewById<Button>(R.id.buttonScrollToDescendant).setOnClickListener {
                val imageView3: ImageView = findViewById(R.id.imageView3)
                scrollView.scrollToDescendant(imageView3)
            }
        }
    }
}
