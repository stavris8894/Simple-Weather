package com.example.simpleweatherapplication.utils.viewholders

import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.ui_data.WeatherCardViewData
import com.example.simpleweatherapplication.utils.extensions.inflate
import com.example.simpleweatherapplication.utils.extensions.toCelsius
import com.example.simpleweatherapplication.utils.extensions.toImgUrl
import com.example.simpleweatherapplication.utils.views.MaskedCardView
import com.google.android.material.textview.MaterialTextView
import com.squareup.picasso.Picasso

@Keep
class CardViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.recycle_view_cards)) {

    fun bindData(weatherCardViewData: WeatherCardViewData) {
        itemView.findViewById<MaterialTextView>(R.id.cardViewTitle).text = weatherCardViewData.title
        itemView.findViewById<MaterialTextView>(R.id.cardViewSubTitle).text = weatherCardViewData.subTitle
        itemView.findViewById<MaterialTextView>(R.id.cardViewDetails).text = weatherCardViewData.details.toCelsius()
        itemView.findViewById<MaskedCardView>(R.id.cardView).setOnClickListener { weatherCardViewData.listener(weatherCardViewData.id) }
        Picasso.get()
            .load(weatherCardViewData.imageUrl.toImgUrl())
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_error_outline_24px)
            .into(itemView.findViewById<AppCompatImageView>(R.id.cardViewImageView))
    }

    companion object {
        private val TAG = CardViewHolder::class.java.simpleName
    }
}
