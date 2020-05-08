package com.example.hellorx4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject


class MainActivity : AppCompatActivity() {

    private lateinit var editText : EditText
    private lateinit var textView : TextView
    private lateinit var button : Button
    private lateinit var subject : PublishSubject<String>
    private lateinit var subscription : Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)

        subject = PublishSubject.create()
        subscription = subject
            .filter {
                it.toInt() < 3
            }
            .subscribe {
            textView.text = "You need to purchase at least 3 items"
        }

        button.setOnClickListener {
            textView.text = ""
            subject.onNext(editText.text.toString())
        }
    }

    override fun onDestroy() {
        subscription.dispose()
        super.onDestroy()
    }
}
