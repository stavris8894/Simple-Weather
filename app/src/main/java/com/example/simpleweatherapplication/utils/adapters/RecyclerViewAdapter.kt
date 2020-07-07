package com.example.simpleweatherapplication.utils.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.ui_data.ButtonViewData
import com.example.simpleweatherapplication.ui_data.HeaderWeatherDetailsViewData
import com.example.simpleweatherapplication.ui_data.WeatherCardViewData
import com.example.simpleweatherapplication.ui_data.WeatherDetailsViewData
import com.example.simpleweatherapplication.utils.RecyclerViewItem
import com.example.simpleweatherapplication.utils.viewholders.*

class RecyclerViewAdapter(
    private var list: List<RecyclerViewItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolderGenerator.createViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].itemType.viewType
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CardViewHolder -> {
                holder.bindData(list[position] as WeatherCardViewData)
            }
            is DetailsViewHolder -> {
                holder.bindData(list[position] as WeatherDetailsViewData)
            }
            is DetailsButtonViewHolder -> {
                holder.bindData(list[position] as ButtonViewData)
            }
            is HeaderDetailsViewHolder -> {
                holder.bindData(list[position] as HeaderWeatherDetailsViewData)
            }
        }
    }
}