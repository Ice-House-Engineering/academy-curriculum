package com.example.hellodatabinding2

import android.widget.ImageView
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods


@BindingMethods(
    BindingMethod(type = ImageView::class, attribute = "app:imageSourceFromBindingMethod", method = "setImageResource")
)
class CustomBindingAdapter