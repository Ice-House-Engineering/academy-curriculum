package com.example.hellopaging1.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hellopaging1.R
import com.example.hellopaging1.model.CryptocurrencyModel


class ListViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {

    private val nameRowTextView : TextView = this.view.findViewById(R.id.nameRowTextView)

    fun bindModel(item : CryptocurrencyModel) {
        this.nameRowTextView.text = item.name
    }
}