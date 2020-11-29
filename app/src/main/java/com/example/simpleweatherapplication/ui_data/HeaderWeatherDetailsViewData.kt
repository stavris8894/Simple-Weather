package com.example.simpleweatherapplication.ui_data

import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.utils.RecyclerViewItemType

data class HeaderWeatherDetailsViewData(
    val title: String,
    val subTitle: String
) : RecyclerViewItem {
    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.HEADER_VIEW
}