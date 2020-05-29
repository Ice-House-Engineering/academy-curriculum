package com.example.hellorx2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject


class MainActivity : AppCompatActivity() {

    private lateinit var editText : EditText
    private lateinit var textView : TextView
    private lateinit var subject : PublishSubject<String>
    private lateinit var subscription : Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        subject = PublishSubject.create()
        subscription = subject.subscribe {
            textView.text = it
        }

        editText.addTextChangedListener {
            subject.onNext(it.toString())
        }
    }

    override fun onDestroy() {
        subscription.dispose()
        super.onDestroy()
    }
}
