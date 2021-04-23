package com.example.simpleweatherapplication.state.results

import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.state.actions.WeatherDetailsAction
import com.example.simpleweatherapplication.state.interfaces.Result

sealed class WeatherDetailsResult : Result {
    data class ShowWeatherDetails(val weatherData: WeatherData) : WeatherDetailsResult()
    data class ActionWrapper(val action: WeatherDetailsAction) : WeatherDetailsResult()
    data class Error(val error: Throwable, val lastAction: WeatherDetailsAction) : WeatherDetailsResult()
}