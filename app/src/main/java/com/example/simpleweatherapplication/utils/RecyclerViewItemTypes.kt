package com.example.simpleweatherapplication.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.databinding.RecycleDetailsButtonBinding
import com.example.simpleweatherapplication.databinding.RecycleDetailsHeaderBinding
import com.example.simpleweatherapplication.databinding.RecycleViewCardsBinding
import com.example.simpleweatherapplication.databinding.RecycleWeatherDetailsBinding
import com.example.simpleweatherapplication.utils.viewholders.CardViewHolder
import com.example.simpleweatherapplication.utils.viewholders.DetailsButtonViewHolder
import com.example.simpleweatherapplication.utils.viewholders.DetailsViewHolder
import com.example.simpleweatherapplication.utils.viewholders.HeaderDetailsViewHolder
import cy.com.core.interfaces.RecyclerViewItemType

enum class RecyclerViewItemTypes(override var viewType: Int) : RecyclerViewItemType {
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
                    CardViewHolder(BindingViewHolder.getBinding(RecycleViewCardsBinding::inflate, parent))
                }
                DETAILS_VIEW -> {
                    DetailsViewHolder(BindingViewHolder.getBinding(RecycleWeatherDetailsBinding::inflate, parent))
                }
                BUTTON_VIEW -> {
                    DetailsButtonViewHolder(BindingViewHolder.getBinding(RecycleDetailsButtonBinding::inflate, parent))
                }
                HEADER_VIEW -> {
                    HeaderDetailsViewHolder(BindingViewHolder.getBinding(RecycleDetailsHeaderBinding::inflate, parent))
                }
            }

        }
    }
}