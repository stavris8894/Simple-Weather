package com.example.simpleweatherapplication.models


data class ApiWeatherResponse(
    val count: Int,
    val `data`: List<Data>
)