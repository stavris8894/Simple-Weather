package com.example.simpleweatherapplication.utils.viewholders

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.ui_data.WeatherCardViewData
import com.example.simpleweatherapplication.utils.extensions.inflate
import com.example.simpleweatherapplication.utils.extensions.toCelsius
import com.example.simpleweatherapplication.utils.extensions.toImgUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycle_view_cards.view.*

class CardViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.recycle_view_cards)) {

    fun bindData(weatherCardViewData: WeatherCardViewData) {
        itemView.cardViewTitle.text = weatherCardViewData.title
        itemView.cardViewSubTitle.text = weatherCardViewData.subTitle
        itemView.cardViewDetails.text = weatherCardViewData.details.toCelsius()
        itemView.setOnClickListener { weatherCardViewData.onClick() }
        Picasso.get()
            .load(weatherCardViewData.imageUrl.toImgUrl())
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_error_outline_24px)
            .into(itemView.cardViewImageView)
    }

    companion object {
        private val TAG = CardViewHolder::class.java.simpleName
    }
}
