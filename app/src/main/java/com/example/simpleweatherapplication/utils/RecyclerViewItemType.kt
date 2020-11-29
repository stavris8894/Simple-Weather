package com.example.simpleweatherapplication.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.utils.viewholders.CardViewHolder
import com.example.simpleweatherapplication.utils.viewholders.DetailsButtonViewHolder
import com.example.simpleweatherapplication.utils.viewholders.DetailsViewHolder
import com.example.simpleweatherapplication.utils.viewholders.HeaderDetailsViewHolder

enum class RecyclerViewItemType(var viewType: Int) {
    CARD_VIEWS(1),
    DETAILS_VIEW(2),
    BUTTON_VIEW(3),
    HEADER_VIEW(4);

    companion object {

        private val map = values().associateBy { it.viewType }

        private fun byViewType(viewType: Int) = map[viewType] ?: error("ViewHolder not found!")

        fun byViewType(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return when (byViewType(viewType)) {
                CARD_VIEWS -> {
                    CardViewHolder(parent)
                }
                DETAILS_VIEW -> {
                    DetailsViewHolder(parent)
                }
                BUTTON_VIEW -> {
                    DetailsButtonViewHolder(parent)
                }
                HEADER_VIEW -> {
                    HeaderDetailsViewHolder(parent)
                }
            }

        }
    }
}