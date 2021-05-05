package com.example.simpleweatherapplication.ui_data

import com.example.simpleweatherapplication.utils.RecyclerViewItemTypes
import cy.com.core.interfaces.RecyclerViewUiItem
import cy.com.core.interfaces.ViewHolderId

class ButtonViewData<T : ViewHolderId>(
    id: T,
    val title: String
) : RecyclerViewUiItem<T>(id) {
    override val itemType: RecyclerViewItemTypes
        get() = RecyclerViewItemTypes.BUTTON_VIEW
    override val data: Any
        get() = title
}