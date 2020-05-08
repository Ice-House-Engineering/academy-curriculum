package com.example.hellotesting2

import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CryptoViewHolder(private val view : View) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private val textView : TextView = this.view.findViewById(R.id.textView)

    init {
        view.setOnClickListener(this)
    }

    fun bindModel(item : String) {
        this.textView.text = item
    }

    override fun onClick(view: View) {
        val context = view.context
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("text", this.textView.text)
        context.startActivity(intent)
    }
}