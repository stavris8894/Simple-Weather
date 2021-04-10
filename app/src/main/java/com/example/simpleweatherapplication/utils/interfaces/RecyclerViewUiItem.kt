package com.example.simpleweatherapplication.utils.interfaces

abstract class RecyclerViewUiItem<T : ViewHolderId>(
    override val id: T
) : RecyclerViewItem
