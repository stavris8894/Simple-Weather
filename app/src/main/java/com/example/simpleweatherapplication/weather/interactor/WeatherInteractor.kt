package com.example.simpleweatherapplication.weather.interactor

import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.utils.models.ResultWrapper
import com.example.simpleweatherapplication.weather.repositories.WeatherDatabaseRepository
import com.example.simpleweatherapplication.weather.repositories.WeatherRemoteRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow

class WeatherInteractor(
    private val weatherRepository: WeatherRemoteRepository,
    private val weatherDatabaseRepository: WeatherDatabaseRepository
) {
    suspend fun getCityWeatherData(address: String, country: String? = null) {
        coroutineScope {
            weatherRepository.getWeatherData(address, country).let { it ->
                it.data.let {
                    weatherDatabaseRepository.insert(it[0])
                }
            }
//            when (val weatherData = weatherRepository.getWeatherData(address, country)) {
//                is ResultWrapper.Success -> {
//                    weatherData.value?.data?.let {
//                        weatherDatabaseRepository.insert(it[0])
//                    }
//                }
//                is ResultWrapper.NetworkError -> {
//
//                }
//                is ResultWrapper.Error -> {
//
//                }
//            }
        }
    }

    fun updateWeathers() {

    }

    fun getDataFromDao(): Flow<List<WeatherData>> {
        return weatherDatabaseRepository.getAllFlow()
    }

}