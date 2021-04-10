package com.example.simpleweatherapplication.utils.extensions

import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewUiItem
import com.example.simpleweatherapplication.utils.interfaces.ViewHolderId


fun RecyclerViewItem.hasId(id: ViewHolderId): Boolean {
    return try {
        (this as RecyclerViewUiItem<*>?)?.id == id
    } catch (e: ClassCastException) {
        false
    }
}
