package com.example.simpleweatherapplication.ui_data

import cy.com.core.models.WeatherData
import com.example.simpleweatherapplication.utils.RecyclerViewItemTypes
import cy.com.core.interfaces.RecyclerViewItemType
import cy.com.core.interfaces.RecyclerViewUiItem
import cy.com.core.interfaces.ViewHolderId

class WeatherCardViewData<T : ViewHolderId>(
    id: T,
    override val data: WeatherData,
) : RecyclerViewUiItem<T>(id) {
    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemTypes.CARD_VIEWS
}


