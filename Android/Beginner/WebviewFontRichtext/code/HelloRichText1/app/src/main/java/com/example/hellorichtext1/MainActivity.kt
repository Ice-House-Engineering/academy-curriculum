package com.example.hellorichtext1

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView : TextView = findViewById(R.id.textView)
        val spannable = SpannableString(textView.text)

        findViewById<Button>(R.id.button).setOnClickListener {
            val backgroundSpans = spannable.getSpans(0, spannable.length, BackgroundColorSpan::class.java)
            spannable.removeSpan(backgroundSpans[0])
            val foregroundSpans = spannable.getSpans(0, spannable.length, ForegroundColorSpan::class.java)
            spannable.removeSpan(foregroundSpans[0])
            val styleSpans = spannable.getSpans(0, spannable.length, StyleSpan::class.java)
            spannable.removeSpan(styleSpans[0])
            textView.text = spannable
        }

        val backgroundColor = ContextCompat.getColor(this, R.color.colorPrimary)
        val whiteColor = ContextCompat.getColor(this, R.color.colorWhite)

        spannable.setSpan(BackgroundColorSpan(backgroundColor), 6, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(ForegroundColorSpan(whiteColor), 6, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(Typeface.ITALIC), 6, 11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannable
    }

}
