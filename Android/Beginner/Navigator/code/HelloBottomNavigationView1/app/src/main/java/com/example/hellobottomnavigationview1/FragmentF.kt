package com.example.hellobottomnavigationview1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class FragmentF : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val result = inflater.inflate(R.layout.fragment_f, container, false)
        return result
    }
}