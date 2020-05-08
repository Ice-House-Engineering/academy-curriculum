package com.example.hellofragment5

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CryptocurrencyViewHolder(private val view : View, rowClicked: (CryptocurrencyModel) -> Unit) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private val nameRowTextView : TextView = this.view.findViewById(R.id.nameRowTextView)
    private val urlRowTextView : TextView = this.view.findViewById(R.id.urlRowTextView)
    private var rowClicked : (CryptocurrencyModel) -> Unit
    private lateinit var model: CryptocurrencyModel

    init {
        view.setOnClickListener(this)
        this.rowClicked = rowClicked
    }

    fun bindModel(item : CryptocurrencyModel) {
        this.nameRowTextView.text = item.name
        this.urlRowTextView.text = item.url
        this.model = item
    }

    override fun onClick(view : View) {
        rowClicked(this.model)
    }
}