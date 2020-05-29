package com.example.hellorecyclerview5

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CryptoViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {

    private val textView : TextView = this.view.findViewById(R.id.textView)

    fun bindModel(item : String) {
        this.textView.text = item
    }
}