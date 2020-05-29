package com.example.hellogesture3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat

const val DEBUG_TAG = "gesture-tag"


class MainActivity : AppCompatActivity() {

    private lateinit var mDetector: GestureDetectorCompat

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDetector = GestureDetectorCompat(this, SimpleGestureListener())
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (mDetector.onTouchEvent(event)) {
            true
        } else {
            super.onTouchEvent(event)
        }
    }

    private class SimpleGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onLongPress(event: MotionEvent) {
            Log.d(DEBUG_TAG, "onLongPress: $event")
        }
    }

}
