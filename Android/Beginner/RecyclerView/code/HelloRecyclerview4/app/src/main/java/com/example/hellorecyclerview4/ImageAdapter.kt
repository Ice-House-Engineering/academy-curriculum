package com.example.hellorecyclerview4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class ImageAdapter(private val imageDataset: Array<String>) : RecyclerView.Adapter<ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ImageViewHolder {
        val linearLayout = LayoutInflater.from(parent.context).inflate(R.layout.image_card, parent, false)
        return ImageViewHolder(linearLayout)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindModel(imageDataset[position])
    }

    override fun getItemCount() = imageDataset.size
}