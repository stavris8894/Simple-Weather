package com.example.simpleweatherapplication.utils.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.ui_data.ButtonViewData
import com.example.simpleweatherapplication.ui_data.HeaderWeatherDetailsViewData
import com.example.simpleweatherapplication.ui_data.WeatherDetailsViewData
import cy.com.core.interfaces.ViewHolderId
import com.example.simpleweatherapplication.utils.viewholders.DetailsButtonViewHolder
import com.example.simpleweatherapplication.utils.viewholders.DetailsViewHolder
import com.example.simpleweatherapplication.utils.viewholders.HeaderDetailsViewHolder

class WeatherDetailsAdapter : GeneralRecyclerViewAdapter() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DetailsViewHolder -> {
                holder.data = getItem(position) as WeatherDetailsViewData<out ViewHolderId>
            }
            is DetailsButtonViewHolder -> {
                holder.data = getItem(position) as ButtonViewData<out ViewHolderId>
                holder.listener = listener
            }
            is HeaderDetailsViewHolder -> {
                holder.data = getItem(position) as HeaderWeatherDetailsViewData<out ViewHolderId>
            }
        }
    }
}