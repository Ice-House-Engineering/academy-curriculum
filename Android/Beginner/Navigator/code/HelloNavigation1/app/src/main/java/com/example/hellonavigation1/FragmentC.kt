package com.example.hellonavigation1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class FragmentC : Fragment() {

    private lateinit var button_go_to_fragment_b: Button
    private lateinit var button_go_to_fragment_a: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val result = inflater.inflate(R.layout.fragment_c, container, false)
        button_go_to_fragment_a = result.findViewById(R.id.button_fragment_c_go_back_to_fragment_a)
        button_go_to_fragment_b = result.findViewById(R.id.button_fragment_c_go_back_to_fragment_b)
        return result
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("button", FragmentCArgs.fromBundle(arguments!!).parameterFromFragmentB.toString())
        button_go_to_fragment_a.setOnClickListener {
            findNavController().navigate(FragmentCDirections.goBackToFragmentA())
        }
        button_go_to_fragment_b.setOnClickListener{
            findNavController().popBackStack()
        }
    }
}