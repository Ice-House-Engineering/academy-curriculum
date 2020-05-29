package com.example.hellolifecycle4

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class BasicFragment : Fragment() {

    private val debugTag = "fragment-lifecycle"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(debugTag, "onCreateView")
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(debugTag, "onViewCreated")
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(debugTag, "onAttach")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(debugTag, "onDetach")
    }

    override fun onStart() {
        super.onStart()
        Log.d(debugTag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(debugTag, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(debugTag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(debugTag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(debugTag, "onDestroy")
    }

}