package com.example.simpleweatherapplication.weather.repositories

import cy.com.core.models.ApiWeatherResponse
import com.example.simpleweatherapplication.network.ApiWebServices

class WeatherRemoteRepository(private val apiWebServices: ApiWebServices) {

    suspend fun getWeatherData(address: String, country: String?): ApiWeatherResponse {
        return apiWebServices.getForecast(city = address, country = country)
//        return safeApiCall(Dispatchers.IO) {
//            apiWebServices.getForecast(city = address, country = country)
//        }
    }
}