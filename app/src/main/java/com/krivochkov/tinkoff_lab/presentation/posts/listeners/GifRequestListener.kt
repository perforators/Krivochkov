package com.krivochkov.tinkoff_lab.presentation.posts.listeners

import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GifRequestListener(private val callback: Callback? = null)
    : RequestListener<GifDrawable> {

    interface Callback {
        fun onEvent()
    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<GifDrawable>?,
        isFirstResource: Boolean
    ): Boolean {
        callback?.onEvent()
        return false
    }

    override fun onResourceReady(
        resource: GifDrawable?,
        model: Any?,
        target: Target<GifDrawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        callback?.onEvent()
        return false
    }

}