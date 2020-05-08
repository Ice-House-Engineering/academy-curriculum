package com.example.hellorecyclerview1

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CryptoViewHolder(private val view : View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private val textView : TextView = this.view.findViewById(R.id.textView)
    private val button : Button = this.view.findViewById(R.id.button)
    private val switch : Button = this.view.findViewById(R.id.switch1)

    init {
        button.setOnClickListener(this)
    }

    fun bindModel(item : String) {
        this.textView.text = item
        this.button.text = item
        this.switch.text = item
    }

    override fun onClick(view: View) {
        Log.d("recyclerview clicked event", this.textView.text.toString())
    }
}
