package com.example.simpleweatherapplication.utils.viewholders

import com.example.simpleweatherapplication.databinding.RecycleWeatherDetailsBinding
import com.example.simpleweatherapplication.ui_data.WeatherDetailsViewData
import com.example.simpleweatherapplication.utils.BindingViewHolder
import cy.com.core.interfaces.ViewHolderId

class DetailsViewHolder(binding: RecycleWeatherDetailsBinding) : BindingViewHolder<RecycleWeatherDetailsBinding>(binding) {

    var data: WeatherDetailsViewData<out ViewHolderId>? = null
        set(value) {
            field = value
            field?.let {
                binding.titleTextView.text = it.data.title
                binding.detailsTextView.text = it.data.subTitle
            }
        }
}