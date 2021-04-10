package com.example.simpleweatherapplication.utils.interfaces

import com.example.simpleweatherapplication.utils.RecyclerViewItemType


interface RecyclerViewItem {

    val itemType: RecyclerViewItemType

    val id: Any

    val data: Any
}