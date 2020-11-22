package com.example.simpleweatherapplication.models

import com.google.gson.annotations.SerializedName


data class ApiWeatherResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val data: List<Data>
)