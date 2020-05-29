package com.example.helloscrollview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val scrollView : HorizontalScrollView = findViewById(R.id.scrollView)

        findViewById<Button>(R.id.buttonScrollToXY).setOnClickListener {
            scrollView.scrollTo(3200, 0)
        }

        findViewById<Button>(R.id.buttonFullScroll).setOnClickListener {
            scrollView.fullScroll(View.FOCUS_RIGHT)
        }

        findViewById<Button>(R.id.buttonScrollAPage).setOnClickListener {
            scrollView.pageScroll(View.FOCUS_RIGHT)
        }
    }
}
