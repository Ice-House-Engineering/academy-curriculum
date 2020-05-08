package com.example.hellomvvm6.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hellomvvm6.R
import com.example.hellomvvm6.model.CryptocurrencyModel


class ListViewHolder(private val view : View, rowClicked: (CryptocurrencyModel) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private val nameRowTextView : TextView = this.view.findViewById(R.id.nameRowTextView)
    private val titleRowTextView : TextView = this.view.findViewById(R.id.titleRowTextView)
    private var rowClicked : (CryptocurrencyModel) -> Unit
    private lateinit var model: CryptocurrencyModel

    init {
        view.setOnClickListener(this)
        this.rowClicked = rowClicked
    }

    fun bindModel(item : CryptocurrencyModel) {
        this.nameRowTextView.text = item.name
        this.titleRowTextView.text = item.title
        this.model = item
    }

    override fun onClick(view : View) {
        rowClicked(this.model)
    }
}