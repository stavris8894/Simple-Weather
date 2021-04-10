package com.example.simpleweatherapplication.utils.interfaces

interface RecyclerItemData {

    fun isSameData(data: RecyclerItemData): Boolean {
        return this == data
    }
}