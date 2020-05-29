package com.example.hellogesture5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent

const val DEBUG_TAG = "gesture-tag"


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        event.actionMasked.let { action ->
            Log.d(DEBUG_TAG, "The action is ${MotionEvent.actionToString(action)}")

            val xPos = event.getX(0).toInt()
            val yPos = event.getY(0).toInt()
            Log.d(DEBUG_TAG, "Single touch event")
            Log.d(DEBUG_TAG, "Single touch => X Position: $xPos, Y Position: $yPos")
            if (event.pointerCount > 1) {
                val xPos = event.getX(1).toInt()
                val yPos = event.getY(1).toInt()
                Log.d(DEBUG_TAG, "Multitouch event")
                Log.d(DEBUG_TAG, "Multitouch => X Position: $xPos, Y Position: $yPos")
            }
        }

        return true
    }

}
