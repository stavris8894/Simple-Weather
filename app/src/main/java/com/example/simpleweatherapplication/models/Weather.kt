package com.example.simpleweatherapplication.models

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("code")
    val code: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String
)