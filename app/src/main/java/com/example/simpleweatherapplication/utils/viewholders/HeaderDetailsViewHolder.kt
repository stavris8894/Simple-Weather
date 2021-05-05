package com.example.simpleweatherapplication.utils.viewholders

import com.example.simpleweatherapplication.databinding.RecycleDetailsHeaderBinding
import com.example.simpleweatherapplication.ui_data.HeaderWeatherDetailsViewData
import com.example.simpleweatherapplication.utils.BindingViewHolder
import cy.com.core.interfaces.ViewHolderId

class HeaderDetailsViewHolder(binding: RecycleDetailsHeaderBinding) : BindingViewHolder<RecycleDetailsHeaderBinding>(binding) {

    var data: HeaderWeatherDetailsViewData<out ViewHolderId>? = null
        set(value) {
            field = value
            field?.let {
                binding.titleTextView.text = it.data.title
                binding.subTitleTextView.text = it.data.subTitle
            }
        }

}