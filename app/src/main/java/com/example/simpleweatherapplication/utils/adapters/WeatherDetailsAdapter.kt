package com.example.simpleweatherapplication.utils.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.ui_data.ButtonViewData
import com.example.simpleweatherapplication.ui_data.HeaderWeatherDetailsViewData
import com.example.simpleweatherapplication.ui_data.WeatherDetailsViewData
import com.example.simpleweatherapplication.utils.viewholders.DetailsButtonViewHolder
import com.example.simpleweatherapplication.utils.viewholders.DetailsViewHolder
import com.example.simpleweatherapplication.utils.viewholders.HeaderDetailsViewHolder

class WeatherDetailsAdapter : GeneralRecyclerViewAdapter() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DetailsViewHolder -> {
                holder.bindData(getItem(position) as WeatherDetailsViewData)
            }
            is DetailsButtonViewHolder -> {
                holder.bindData(getItem(position) as ButtonViewData)
            }
            is HeaderDetailsViewHolder -> {
                holder.bindData(getItem(position) as HeaderWeatherDetailsViewData)
            }
        }
    }
}