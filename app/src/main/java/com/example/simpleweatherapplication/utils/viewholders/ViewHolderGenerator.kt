package com.example.simpleweatherapplication.utils.viewholders

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.utils.RecyclerViewItemType
import kotlin.reflect.full.primaryConstructor

class ViewHolderGenerator {

    companion object {

        fun createViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return RecyclerViewItemType.byViewType(viewType).clazz.primaryConstructor!!.call(parent)
        }
    }
}