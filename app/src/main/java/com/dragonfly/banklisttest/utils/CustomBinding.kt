package com.dragonfly.banklisttest.utils

import androidx.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView

class CustomBinding {
    companion object{

        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(view: SimpleDraweeView, url: String?) {
            view.setImageURI(url)
        }
    }
}