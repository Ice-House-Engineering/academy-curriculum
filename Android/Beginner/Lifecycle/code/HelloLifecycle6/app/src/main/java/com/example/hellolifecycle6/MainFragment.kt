package com.example.hellolifecycle6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get


class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            val vm: BasicViewModel = ViewModelProviders.of(it).get()
            view.findViewById<TextView>(R.id.textView).text = vm.cryptocurrency
            view.findViewById<Button>(R.id.button).setOnClickListener {
                fragmentManager?.beginTransaction()!!.replace(android.R.id.content, DetailFragment())
                    .addToBackStack(null).commit()
            }
        }
    }
}