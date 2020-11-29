package com.example.simpleweatherapplication.utils.viewholders

import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.ui_data.WeatherDetailsViewData
import com.example.simpleweatherapplication.utils.extensions.inflate
import com.google.android.material.textview.MaterialTextView

@Keep
class DetailsViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.recycle_weather_details)) {

    fun bindData(weatherDetailsViewData: WeatherDetailsViewData) {
        itemView.findViewById<MaterialTextView>(R.id.titleTextView).text = weatherDetailsViewData.title
        itemView.findViewById<MaterialTextView>(R.id.detailsTextView).text = weatherDetailsViewData.details
    }
}