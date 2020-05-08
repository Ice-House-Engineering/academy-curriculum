package com.example.helloanimation13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    var showingBack = false

    class CardFrontFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View = inflater.inflate(R.layout.fragment_card_front, container, false)

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            val button : Button = getView()!!.findViewById(R.id.button)
            button.setOnClickListener {
                val mainActivity = activity as MainActivity
                mainActivity.flipCard()
            }
        }
    }

    class CardBackFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View = inflater.inflate(R.layout.fragment_card_back, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, CardFrontFragment())
                .commit()
        }

    }

    private fun flipCard() {
        if (showingBack) {
            supportFragmentManager.popBackStack()
            return
        }

        showingBack = true

        supportFragmentManager.beginTransaction()

            .setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out
            )

            .replace(R.id.container, CardBackFragment())

            .addToBackStack(null)

            .commit()
    }

}
