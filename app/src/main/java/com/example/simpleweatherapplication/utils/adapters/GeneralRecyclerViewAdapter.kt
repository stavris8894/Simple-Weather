package com.example.simpleweatherapplication.utils.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.utils.RecyclerViewItemType
import com.example.simpleweatherapplication.utils.interfaces.RecyclerItemData
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem

abstract class GeneralRecyclerViewAdapter : ListAdapter<RecyclerViewItem, RecyclerView.ViewHolder>(TASKS_COMPARATOR) {

    open var listener: ((RecyclerViewItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RecyclerViewItemType.byViewType(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType.viewType
    }

    fun findItem(predicate: (RecyclerViewItem) -> Boolean): RecyclerViewItem? {
        return currentList.find(predicate)
    }

    fun getIndexOfItem(item: RecyclerViewItem): Int {
        return currentList.indexOf(item)
    }

    companion object {
        private val TAG = GeneralRecyclerViewAdapter::class.simpleName
        private val TASKS_COMPARATOR = object : DiffUtil.ItemCallback<RecyclerViewItem>() {
            override fun areItemsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean {
                val oldData = oldItem.data
                val newData = newItem.data
                return if ((oldData is RecyclerItemData) && (newData is RecyclerItemData)) {
                    oldData.isSameData(newData)
                } else
                    oldData == newData
            }
        }
    }
}