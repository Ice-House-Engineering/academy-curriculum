package com.example.hellopaging1.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.hellopaging1.R
import com.example.hellopaging1.model.CryptocurrencyModel


class ListAdapter() : PagedListAdapter<CryptocurrencyModel, ListViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.fragment_row, parent, false)
        return ListViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val cryptocurrencyModel: CryptocurrencyModel? = getItem(position)
        cryptocurrencyModel?.let {
            holder.bindModel(it)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<CryptocurrencyModel>() {
            override fun areItemsTheSame(oldCryptocurrencyModel: CryptocurrencyModel,
                                         newCryptocurrencyModel: CryptocurrencyModel) = oldCryptocurrencyModel.id == newCryptocurrencyModel.id

            override fun areContentsTheSame(oldCryptocurrencyModel: CryptocurrencyModel,
                                            newCryptocurrencyModel: CryptocurrencyModel) = oldCryptocurrencyModel == newCryptocurrencyModel
        }
    }

}