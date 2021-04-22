package com.example.simpleweatherapplication.state.actions

import com.example.simpleweatherapplication.state.interfaces.Action

sealed class WeatherAction : Action {
    data class FetchWeatherFromApi(val cityName: String, val shortName: String?) : WeatherAction()
    object GetWeather : WeatherAction()
    object UpdateWeathers : WeatherAction()
    object CleanError : WeatherAction()
}