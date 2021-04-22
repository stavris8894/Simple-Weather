package com.example.simpleweatherapplication.weather.interactor

import com.example.simpleweatherapplication.models.WeatherData
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
        }
    }

    suspend fun removeWeatherData(weatherData: WeatherData) {
        coroutineScope {
            weatherDatabaseRepository.deleteWeather(weatherData)
        }
    }

    suspend fun updateWeathers() {
        coroutineScope {
            val weatherData = weatherDatabaseRepository.getAllOneTime()
            if (weatherData.isEmpty()) {
                return@coroutineScope
            }
            weatherData.forEach {
                getCityWeatherData(it.cityName, it.countryCode)
            }
        }

    }

    fun getDataFromDao(): Flow<List<WeatherData>> {
        return weatherDatabaseRepository.getAllFlow()
    }

}