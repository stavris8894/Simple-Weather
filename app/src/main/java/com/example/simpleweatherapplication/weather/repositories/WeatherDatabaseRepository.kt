package com.example.simpleweatherapplication.weather.repositories

import android.app.Application
import cy.com.core.models.WeatherData
import cy.com.core.room.WeatherDao
import cy.com.core.room.WeatherDatabase
import kotlinx.coroutines.flow.Flow

class WeatherDatabaseRepository(application: Application) {

    private var weatherDao: WeatherDao = WeatherDatabase.getInstance(application).weatherDao()

    suspend fun deleteAll() {
        weatherDao.deleteAll()
    }

    suspend fun deleteWeather(weatherData: WeatherData) {
        weatherDao.deleteWeather(weatherData)
    }

    suspend fun insertAll(list: List<WeatherData>) {
        weatherDao.insertAll(list)
    }

    suspend fun insert(weatherData: WeatherData) {
        weatherDao.insert(weatherData)
    }

    suspend fun getAllOneTime(): List<WeatherData> {
        return weatherDao.getAllOneTime()
    }

    fun getAllFlow(): Flow<List<WeatherData>> {
        return weatherDao.getAllFlow()
    }

    fun getByCityName(city: String): Flow<WeatherData> {
        return weatherDao.getByCityName(city)
    }

}