package com.example.simpleweatherapplication.utils.viewholders

import com.example.simpleweatherapplication.databinding.RecycleDetailsButtonBinding
import com.example.simpleweatherapplication.ui_data.ButtonViewData
import com.example.simpleweatherapplication.utils.BindingViewHolder
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.utils.interfaces.ViewHolderId

class DetailsButtonViewHolder(binding: RecycleDetailsButtonBinding) : BindingViewHolder<RecycleDetailsButtonBinding>(binding) {
    var listener: ((RecyclerViewItem) -> Unit)? = null

    var data: ButtonViewData<out ViewHolderId>? = null
        set(value) {
            field = value
            field?.let { data ->
                binding.doneButton.apply {
                    text = data.title
                    setOnClickListener {
                        listener?.invoke(data)
                    }
                }
            }
        }
}