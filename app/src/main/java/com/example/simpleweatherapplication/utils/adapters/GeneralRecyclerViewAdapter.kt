package com.example.simpleweatherapplication.utils.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.utils.RecyclerViewItemType

abstract class GeneralRecyclerViewAdapter : ListAdapter<RecyclerViewItem, RecyclerView.ViewHolder>(TASKS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewItemType.byViewType(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType.viewType
    }

    companion object {
        private val TAG = GeneralRecyclerViewAdapter::class.simpleName
        private val TASKS_COMPARATOR = object : DiffUtil.ItemCallback<RecyclerViewItem>() {
            override fun areItemsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
                return oldItem.itemType == newItem.itemType
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}