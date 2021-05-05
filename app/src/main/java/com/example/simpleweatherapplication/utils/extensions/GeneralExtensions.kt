package com.example.simpleweatherapplication.utils.extensions

import cy.com.core.interfaces.RecyclerViewItem
import cy.com.core.interfaces.RecyclerViewUiItem
import cy.com.core.interfaces.ViewHolderId


fun RecyclerViewItem.hasId(id: ViewHolderId): Boolean {
    return try {
        (this as RecyclerViewUiItem<*>?)?.id == id
    } catch (e: ClassCastException) {
        false
    }
}
