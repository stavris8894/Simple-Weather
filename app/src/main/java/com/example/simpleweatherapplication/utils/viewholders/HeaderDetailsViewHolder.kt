package com.example.simpleweatherapplication.utils.viewholders

import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.ui_data.HeaderWeatherDetailsViewData
import com.example.simpleweatherapplication.utils.extensions.inflate
import com.google.android.material.textview.MaterialTextView

@Keep
class HeaderDetailsViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.recycle_details_header)) {

    fun bindData(headerWeatherDetailsViewData: HeaderWeatherDetailsViewData) {
        itemView.findViewById<MaterialTextView>(R.id.titleTextView).text = headerWeatherDetailsViewData.title
        itemView.findViewById<MaterialTextView>(R.id.subTitleTextView).text = headerWeatherDetailsViewData.subTitle
    }
}