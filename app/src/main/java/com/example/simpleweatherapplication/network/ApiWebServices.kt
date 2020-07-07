package com.example.simpleweatherapplication.network

import com.example.simpleweatherapplication.models.ApiWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiWebServices {

    @GET("current")
    suspend fun getForecast(
        @Query("city") city: String = "Nicosia, Cyprus",
        @Query("country") country: String? = null
    ): Response<ApiWeatherResponse?>
}