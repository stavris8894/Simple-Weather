package com.example.simpleweatherapplication.state.viewstates

import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.state.interfaces.ViewState
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem

data class WeatherDetailsViewState(
    val weatherData: List<RecyclerViewItem> = listOf(),
    override val error: Throwable? = null
) : ViewState()