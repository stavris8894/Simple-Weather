package com.example.simpleweatherapplication.utils.viewholders

import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.ui_data.WeatherDetailsViewData
import com.example.simpleweatherapplication.utils.extensions.inflate
import kotlinx.android.synthetic.main.recycle_weather_details.view.*

@Keep
class DetailsViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.recycle_weather_details)) {

    fun bindData(weatherDetailsViewData: WeatherDetailsViewData) {
        itemView.titleTextView.text = weatherDetailsViewData.title
        itemView.detailsTextView.text = weatherDetailsViewData.details
    }
}