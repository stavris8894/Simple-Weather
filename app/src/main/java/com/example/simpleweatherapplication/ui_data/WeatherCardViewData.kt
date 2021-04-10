package com.example.simpleweatherapplication.ui_data

import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.utils.RecyclerViewItemType
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewUiItem
import com.example.simpleweatherapplication.utils.interfaces.ViewHolderId

class WeatherCardViewData<T : ViewHolderId>(
    id: T,
    override val data: WeatherData,
) : RecyclerViewUiItem<T>(id) {
    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.CARD_VIEWS
}


