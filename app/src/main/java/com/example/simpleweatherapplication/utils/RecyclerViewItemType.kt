package com.example.simpleweatherapplication.utils

import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.utils.viewholders.CardViewHolder
import com.example.simpleweatherapplication.utils.viewholders.DetailsButtonViewHolder
import com.example.simpleweatherapplication.utils.viewholders.DetailsViewHolder
import com.example.simpleweatherapplication.utils.viewholders.HeaderDetailsViewHolder
import kotlin.reflect.KClass

enum class RecyclerViewItemType(var viewType: Int, val clazz: KClass<out RecyclerView.ViewHolder>) {
    CARD_VIEWS(1, CardViewHolder::class),
    DETAILS_VIEW(2, DetailsViewHolder::class),
    BUTTON_VIEW(3, DetailsButtonViewHolder::class),
    HEADER_VIEW(4, HeaderDetailsViewHolder::class);

    companion object {

        private val map = values().associateBy { it.viewType }

        fun byViewType(viewType: Int) = map[viewType] ?: error("ViewHolder not found!")
    }
}