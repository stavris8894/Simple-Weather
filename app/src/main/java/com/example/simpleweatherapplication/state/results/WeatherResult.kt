package com.example.simpleweatherapplication.state.results

import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.state.actions.WeatherAction
import com.example.simpleweatherapplication.state.interfaces.Result

sealed class WeatherResult : Result {
    data class AddWeathers(val weatherData: List<WeatherData>) : WeatherResult()
    data class Error(val error: Throwable, val lastAction: WeatherAction) : WeatherResult()
    data class ActionWrapper(val action: WeatherAction) : WeatherResult()
}