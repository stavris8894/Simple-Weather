package com.example.simpleweatherapplication.ui_data

import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.utils.RecyclerViewItemType

data class WeatherDetailsViewData(
    val title: String,
    val details: String
) : RecyclerViewItem {
    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.DETAILS_VIEW
}