package com.example.simpleweatherapplication.state.actions

import com.example.simpleweatherapplication.state.interfaces.Action

sealed class WeatherAction : Action {
    object GetWeather : WeatherAction()
    object CleanError : WeatherAction()
}