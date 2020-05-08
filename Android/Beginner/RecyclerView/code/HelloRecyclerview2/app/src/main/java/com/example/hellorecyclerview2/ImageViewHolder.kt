package com.example.hellorecyclerview2

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ImageViewHolder(private val view : View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private val textView : TextView = this.view.findViewById(R.id.textView)
    private val imageView : ImageView = this.view.findViewById(R.id.imageView)

    init {
        view.setOnClickListener(this)
    }

    fun bindModel(item : String) {
        this.textView.text = item + " " + item
        val imageResource = this.view.context.resources.getIdentifier("@drawable/" + item, null, this.view.context.packageName)
        this.imageView.setImageResource(imageResource)
    }

    override fun onClick(view : View) {
        Log.d("row", this.textView.text.toString())
    }
}