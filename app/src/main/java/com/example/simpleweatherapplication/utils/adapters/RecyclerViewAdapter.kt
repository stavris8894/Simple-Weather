package com.example.simpleweatherapplication.utils.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.ui_data.ButtonViewData
import com.example.simpleweatherapplication.ui_data.HeaderWeatherDetailsViewData
import com.example.simpleweatherapplication.ui_data.WeatherCardViewData
import com.example.simpleweatherapplication.ui_data.WeatherDetailsViewData
import com.example.simpleweatherapplication.utils.RecyclerViewItem
import com.example.simpleweatherapplication.utils.viewholders.*

class RecyclerViewAdapter : ListAdapter<RecyclerViewItem, RecyclerView.ViewHolder>(TASKS_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderGenerator.createViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).itemType.viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CardViewHolder -> {
                holder.bindData(getItem(position) as WeatherCardViewData)
            }
            is DetailsViewHolder -> {
                holder.bindData(getItem(position) as WeatherDetailsViewData)
            }
            is DetailsButtonViewHolder -> {
                holder.bindData(getItem(position) as ButtonViewData)
            }
            is HeaderDetailsViewHolder -> {
                holder.bindData(getItem(position) as HeaderWeatherDetailsViewData)
            }
        }
    }

    companion object {
        private val TASKS_COMPARATOR = object : DiffUtil.ItemCallback<RecyclerViewItem>() {
            override fun areItemsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean =
                oldItem.itemType == newItem.itemType

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: RecyclerViewItem, newItem: RecyclerViewItem): Boolean =
                oldItem == newItem
        }
    }
}