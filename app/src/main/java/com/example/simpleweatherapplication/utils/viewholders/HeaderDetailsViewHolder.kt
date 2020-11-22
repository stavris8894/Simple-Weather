package com.example.simpleweatherapplication.utils.viewholders

import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.ui_data.HeaderWeatherDetailsViewData
import com.example.simpleweatherapplication.utils.extensions.inflate
import kotlinx.android.synthetic.main.recycle_details_header.view.*

@Keep
class HeaderDetailsViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.recycle_details_header)) {

    fun bindData(headerWeatherDetailsViewData: HeaderWeatherDetailsViewData) {
        itemView.titleTextView.text = headerWeatherDetailsViewData.title
        itemView.subTitleTextView.text = headerWeatherDetailsViewData.subTitle
    }
}