package com.example.helloglide2

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class CryptocurrencyViewHolder(private val view : View, private val context: Context) : RecyclerView.ViewHolder(view) {

    private val textView : TextView = this.view.findViewById(R.id.textView)
    private val imageView : ImageView = this.view.findViewById(R.id.imageView)

    fun bindModel(item: Pair<String, String>) {
        this.textView.text = item.first
        Glide.with(context)
            .load(item.second)
            .into(imageView)
    }
}