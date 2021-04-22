package com.example.simpleweatherapplication.weather.repositories

import com.example.simpleweatherapplication.models.ApiWeatherResponse
import com.example.simpleweatherapplication.network.ApiWebServices
import com.example.simpleweatherapplication.utils.models.ResultWrapper
import com.example.simpleweatherapplication.utils.models.safeApiCall
import kotlinx.coroutines.Dispatchers

class WeatherRemoteRepository(private val apiWebServices: ApiWebServices) {

    suspend fun getWeatherData(address: String, country: String?): ApiWeatherResponse {
        return apiWebServices.getForecast(city = address, country = country)
//        return safeApiCall(Dispatchers.IO) {
//            apiWebServices.getForecast(city = address, country = country)
//        }
    }
}