package com.example.hellorx15

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.PublishSubject


class CryptoAdapter(private val cryptoDataset: Array<String>) : RecyclerView.Adapter<CryptoViewHolder>() {

    val subject = PublishSubject.create<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : CryptoViewHolder {
        val linearLayout = LayoutInflater.from(parent.context).inflate(R.layout.crypto_row, parent, false)
        return CryptoViewHolder(linearLayout)
    }

    override fun onBindViewHolder(holder: CryptoViewHolder, position: Int) {
        holder.bindModel(cryptoDataset[position])
        holder.itemView.setOnClickListener {
            subject.onNext(cryptoDataset[position])
        }
    }

    override fun getItemCount() = cryptoDataset.size

}