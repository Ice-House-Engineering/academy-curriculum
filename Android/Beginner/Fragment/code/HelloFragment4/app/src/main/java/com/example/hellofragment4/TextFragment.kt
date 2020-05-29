package com.example.hellofragment4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get


class TextFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result = inflater.inflate(R.layout.fragment_text, container, false)
        val textView = result.findViewById<TextView>(R.id.textView)
        val viewModel: BasicViewModel = ViewModelProviders.of(this).get()
        textView.text = viewModel.cryptocurrency
        return result
    }
}