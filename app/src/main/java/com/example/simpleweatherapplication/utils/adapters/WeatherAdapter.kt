package com.example.simpleweatherapplication.utils.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.ui_data.WeatherCardViewData
import cy.com.core.interfaces.RecyclerViewItem
import cy.com.core.interfaces.ViewHolderId
import com.example.simpleweatherapplication.utils.viewholders.CardViewHolder

class WeatherAdapter : GeneralRecyclerViewAdapter() {

    var removelistener: ((RecyclerViewItem) -> Unit)? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CardViewHolder -> {
                holder.data = (getItem(position) as WeatherCardViewData<out ViewHolderId>)
                holder.listener = listener
                holder.removelistener = removelistener
            }
        }
    }
}