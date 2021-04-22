package com.example.simpleweatherapplication.state.actions

import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.state.interfaces.Action

sealed class WeatherAction : Action {
    data class FetchWeatherFromApi(val cityName: String, val shortName: String?) : WeatherAction()
    data class RemoveWeather(val weatherData: WeatherData) : WeatherAction()
    object GetWeather : WeatherAction()
    object UpdateWeathers : WeatherAction()
    object CleanError : WeatherAction()
}