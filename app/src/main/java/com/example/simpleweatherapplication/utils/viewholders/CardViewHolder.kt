package com.example.simpleweatherapplication.utils.viewholders

import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.databinding.RecycleViewCardsBinding
import com.example.simpleweatherapplication.ui_data.WeatherCardViewData
import com.example.simpleweatherapplication.utils.BindingViewHolder
import com.example.simpleweatherapplication.utils.extensions.toCelsius
import com.example.simpleweatherapplication.utils.extensions.toImgUrl
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.utils.interfaces.ViewHolderId
import com.squareup.picasso.Picasso

class CardViewHolder(binding: RecycleViewCardsBinding) : BindingViewHolder<RecycleViewCardsBinding>(binding) {

    var listener: ((RecyclerViewItem) -> Unit)? = null

    var data: WeatherCardViewData<out ViewHolderId>? = null
        set(value) {
            field = value
            field?.let { weatherModel ->
                binding.cardViewTitle.text = weatherModel.data.cityName
                binding.cardViewSubTitle.text = weatherModel.data.countryCode
                binding.cardViewDetails.text = weatherModel.data.temp.toString().toCelsius()
                binding.cardView.setOnClickListener { listener?.invoke(weatherModel) }
                Picasso.get()
                    .load(weatherModel.data.weather.icon.toImgUrl())
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.ic_error_outline_24px)
                    .into(binding.cardViewImageView)
            }
        }

    companion object {
        private val TAG = CardViewHolder::class.java.simpleName
    }
}
