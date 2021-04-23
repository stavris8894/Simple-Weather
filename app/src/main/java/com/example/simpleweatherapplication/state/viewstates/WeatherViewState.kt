package com.example.simpleweatherapplication.state.viewstates

import com.example.simpleweatherapplication.state.interfaces.ViewState
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem

data class WeatherViewState(
    val recycleViewItems: List<RecyclerViewItem> = listOf(),
    val showProgressBar: Boolean = true,
    override val error: Throwable? = null
) : ViewState()