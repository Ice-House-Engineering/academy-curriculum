package com.example.helloglide2

import android.content.Context
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import java.util.*


class ImagePreloadModelProvider (private val listUrls: List<Pair<String, String>>,
                                 private val context: Context,
                                 private val imageWidth: Int,
                                 private val imageHeight: Int) :
    ListPreloader.PreloadModelProvider<Any> {

    override fun getPreloadItems(position: Int): MutableList<Any> {
        val url = listUrls.get(position)
        if (TextUtils.isEmpty(url.first)) {
            return Collections.emptyList()
        }
        return Collections.singletonList(url.first)
    }

    override fun getPreloadRequestBuilder(item: Any): RequestBuilder<*>? {
        return Glide.with(context)
            .load(item)
            .override(imageWidth, imageHeight)
    }

}