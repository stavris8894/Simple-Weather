package com.example.simpleweatherapplication.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BindingViewHolder<out T : ViewBinding>(
    protected val binding: T
) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        inline fun <reified T : ViewBinding> getBinding(
            inflate: (inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean) -> T,
            parent: ViewGroup
        ): T {
            return inflate(LayoutInflater.from(parent.context), parent, false)
        }
    }
}