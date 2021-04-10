package com.example.simpleweatherapplication.ui_data

import com.example.simpleweatherapplication.utils.RecyclerViewItemType
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewUiItem
import com.example.simpleweatherapplication.utils.interfaces.ViewHolderId

class WeatherDetailsViewData<T : ViewHolderId>(
    id: T,
    override val data: TitleSubtitleDataModel
) : RecyclerViewUiItem<T>(id) {

    override val itemType: RecyclerViewItemType
        get() = RecyclerViewItemType.DETAILS_VIEW
}