package com.example.hellofragment1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class ButtonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val result = inflater.inflate(R.layout.fragment_button, container, false)
        result.findViewById<Button>(R.id.button).setOnClickListener {
            (activity as MainActivity).buttonCallback()
        }
        return result
    }

}
