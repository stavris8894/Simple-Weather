package com.example.simpleweatherapplication.weather.repositories

import android.app.Application
import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.room.WeatherDao
import com.example.simpleweatherapplication.room.WeatherDatabase
import kotlinx.coroutines.flow.Flow

class WeatherDatabaseRepository(application: Application) {

    private var weatherDao: WeatherDao = WeatherDatabase.getInstance(application).weatherDao()

    suspend fun deleteAll() {
        weatherDao.deleteAll()
    }

    suspend fun insertAll(list: List<WeatherData>) {
        weatherDao.insertAll(list)
    }

    suspend fun insert(weatherData: WeatherData) {
        weatherDao.insert(weatherData)
    }

    fun getAll(): Flow<List<WeatherData>> {
        return weatherDao.getAll()
    }

    fun getByCityName(city: String): Flow<WeatherData> {
        return weatherDao.getByCityName(city)
    }

}