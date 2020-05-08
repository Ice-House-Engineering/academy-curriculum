package com.example.helloanimation17

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ScreenSlidePageFragment : Fragment() {

    companion object {
        fun newInstance(text: String) = ScreenSlidePageFragment().apply {
            arguments = Bundle(1).apply {
                putString("text", text)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getView()!!.findViewById<TextView>(R.id.textView).text = arguments!!.getString("text")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_screen_slide_page, container, false)
}