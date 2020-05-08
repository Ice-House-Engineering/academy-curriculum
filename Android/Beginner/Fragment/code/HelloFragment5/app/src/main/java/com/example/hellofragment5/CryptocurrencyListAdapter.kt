package com.example.hellofragment5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CryptocurrencyListAdapter(private val dataset: List<CryptocurrencyModel>, private val rowClicked: (CryptocurrencyModel) -> Unit) : RecyclerView.Adapter<CryptocurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptocurrencyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_row, parent, false)
        return CryptocurrencyViewHolder(layout, rowClicked)
    }

    override fun onBindViewHolder(holder: CryptocurrencyViewHolder, position: Int) {
        holder.bindModel(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.count()
    }
}