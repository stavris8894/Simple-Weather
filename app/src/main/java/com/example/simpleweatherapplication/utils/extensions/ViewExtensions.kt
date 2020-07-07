package com.example.simpleweatherapplication.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

var View.useDefaultLayoutParams: Boolean
    get() = false
    set(value) {
        if (value) {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

fun ViewGroup.inflate(@LayoutRes resourceId: Int): View {
    return LayoutInflater.from(context).inflate(resourceId, this, false)
}

