package com.example.simpleweatherapplication.ui_data

import com.example.simpleweatherapplication.utils.RecyclerViewItemTypes
import cy.com.core.interfaces.RecyclerViewItemType
import cy.com.core.interfaces.RecyclerViewUiItem
import cy.com.core.interfaces.ViewHolderId

class HeaderWeatherDetailsViewData<T : ViewHolderId>(
    id: T,
    override val data: TitleSubtitleDataModel
) : RecyclerViewUiItem<T>(id) {
    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemTypes.HEADER_VIEW
}