package com.example.simpleweatherapplication.utils.viewholders

import android.view.ViewGroup
import androidx.annotation.Keep
import androidx.recyclerview.widget.RecyclerView
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.ui_data.ButtonViewData
import com.example.simpleweatherapplication.utils.extensions.inflate
import com.google.android.material.button.MaterialButton

@Keep
class DetailsButtonViewHolder(parent: ViewGroup) :
    RecyclerView.ViewHolder(parent.inflate(R.layout.recycle_details_button)) {

    fun bindData(buttonViewData: ButtonViewData) {
        itemView.findViewById<MaterialButton>(R.id.doneButton).apply {
            text = buttonViewData.title
            setOnClickListener {
                buttonViewData.onClick()
            }
        }
    }
}