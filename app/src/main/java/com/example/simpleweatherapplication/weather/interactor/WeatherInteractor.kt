package com.example.simpleweatherapplication.weather.interactor

import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.weather.repositories.WeatherDatabaseRepository
import com.example.simpleweatherapplication.weather.repositories.WeatherRemoteRepository
import kotlinx.coroutines.flow.Flow

class WeatherInteractor(
    private val weatherRepository: WeatherRemoteRepository,
    private val weatherDatabaseRepository: WeatherDatabaseRepository
) {
//    fun getCityWeatherData(address: String, country: String? = null) {
//        _showProgressBar.value = Event(true)
//        viewModelScope.launch {
//            when (val weatherData = weatherRepository.getWeatherData(address, country)) {
//                is ResultWrapper.Success -> {
//                    weatherData.value?.data?.let {
//                        weatherDatabaseRepository.insert(it[0])
//                    }.also { _showProgressBar.value = Event(false) }
//                }
//                is ResultWrapper.NetworkError -> {
//                    _showErrorMessage.value = Event(SimpleWeatherApp.getString(R.string.network_error)).also { _showProgressBar.value = Event(false) }
//                }
//                is ResultWrapper.Error -> {
//                    weatherData.errorResponse?.let {
//                        _showErrorMessage.value = Event(it.getErrorMessage)
//                    }.also {
//                        _showProgressBar.value = Event(false)
//                    }
//                }
//            }
//        }
//    }

    fun getDataFromDao(): Flow<List<WeatherData>> {
        return weatherDatabaseRepository.getAllFlow()
    }

}