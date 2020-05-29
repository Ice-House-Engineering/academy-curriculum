package com.example.helloglide2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CryptocurrencyAdapter(private val cryptoDataset: List<Pair<String, String>>) : RecyclerView.Adapter<CryptocurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CryptocurrencyViewHolder {
        val linearLayout = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return CryptocurrencyViewHolder(linearLayout, parent.context)
    }

    override fun onBindViewHolder(holder: CryptocurrencyViewHolder, position: Int) {
        holder.bindModel(cryptoDataset[position])
    }

    override fun getItemCount() = cryptoDataset.size
}