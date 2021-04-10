package com.example.simpleweatherapplication.ui_data

import com.example.simpleweatherapplication.utils.RecyclerViewItemType
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewUiItem
import com.example.simpleweatherapplication.utils.interfaces.ViewHolderId

class ButtonViewData<T : ViewHolderId>(
    id: T,
    val title: String
) : RecyclerViewUiItem<T>(id) {
    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.BUTTON_VIEW
    override val data: Any
        get() = title
}