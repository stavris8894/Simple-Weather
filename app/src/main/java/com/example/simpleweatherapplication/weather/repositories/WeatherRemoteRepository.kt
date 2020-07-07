package com.example.simpleweatherapplication.weather.repositories

import com.example.simpleweatherapplication.models.ApiWeatherResponse
import com.example.simpleweatherapplication.models.ResultWrapper
import com.example.simpleweatherapplication.models.safeApiCall
import com.example.simpleweatherapplication.network.ApiWebServices
import kotlinx.coroutines.Dispatchers

class WeatherRemoteRepository(private val apiWebServices: ApiWebServices) {

    suspend fun getWeatherData(address: String, country: String?): ResultWrapper<ApiWeatherResponse?> {
        return safeApiCall(Dispatchers.IO) { apiWebServices.getForecast(city = address, country = country) }
    }
}