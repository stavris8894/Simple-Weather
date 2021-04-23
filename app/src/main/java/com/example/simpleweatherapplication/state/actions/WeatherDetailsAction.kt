package com.example.simpleweatherapplication.state.actions

import com.example.simpleweatherapplication.state.interfaces.Action

sealed class WeatherDetailsAction : Action {
    data class GetWeatherDetails(val id: String) : WeatherDetailsAction()
    object CleanError : WeatherDetailsAction()
}