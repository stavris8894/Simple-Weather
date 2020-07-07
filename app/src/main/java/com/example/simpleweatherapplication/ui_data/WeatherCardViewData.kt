package com.example.simpleweatherapplication.ui_data

import com.example.simpleweatherapplication.utils.RecyclerViewItem
import com.example.simpleweatherapplication.utils.RecyclerViewItemType

data class WeatherCardViewData(
    val title: String,
    val subTitle: String,
    val details: String,
    val imageUrl: String = "error",
    val onClick: () -> Unit
) : RecyclerViewItem {
    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.CARD_VIEWS
}