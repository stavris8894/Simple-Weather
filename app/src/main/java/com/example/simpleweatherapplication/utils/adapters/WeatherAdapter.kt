package com.example.simpleweatherapplication.utils.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.ui_data.WeatherCardViewData
import com.example.simpleweatherapplication.utils.viewholders.CardViewHolder

class WeatherAdapter : GeneralRecyclerViewAdapter() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CardViewHolder -> {
                holder.bindData(getItem(position) as WeatherCardViewData)
            }
        }
    }
}