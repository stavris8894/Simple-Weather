package com.example.simpleweatherapplication.ui_data

import com.example.simpleweatherapplication.utils.RecyclerViewItem
import com.example.simpleweatherapplication.utils.RecyclerViewItemType

data class ButtonViewData(
    val title: String,
    val onClick: () -> Unit
) : RecyclerViewItem {
    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.BUTTON_VIEW
}