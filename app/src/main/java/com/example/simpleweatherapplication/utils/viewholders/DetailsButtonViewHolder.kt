package com.example.simpleweatherapplication.utils.viewholders

import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.ui_data.ButtonViewData
import com.example.simpleweatherapplication.utils.extensions.inflate
import kotlinx.android.synthetic.main.recycle_details_button.view.*

@Keep
class DetailsButtonViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.recycle_details_button)) {

    fun bindData(buttonViewData: ButtonViewData) {
        itemView.doneButton.text = buttonViewData.title
        itemView.doneButton.setOnClickListener {
            buttonViewData.onClick()
        }
    }
}