package com.krivochkov.krivochkov.presentation

import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.krivochkov.krivochkov.R
import java.util.*

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.poster_placeholder)
        .into(this)
}

fun Fragment.getColor(@ColorRes resId: Int): Int {
    return ContextCompat.getColor(requireContext(), resId)
}

fun String.toCapital() = replaceFirstChar { it.titlecase(Locale.getDefault()) }