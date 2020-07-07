package com.example.simpleweatherapplication.weather.repositories

import android.app.Application
import com.example.simpleweatherapplication.models.Data
import com.example.simpleweatherapplication.room.WeatherDao
import com.example.simpleweatherapplication.room.WeatherDatabase
import kotlinx.coroutines.flow.Flow

class WeatherDatabaseRepository(application: Application) {

    private var weatherDao: WeatherDao = WeatherDatabase.getInstance(application).weatherDao()

    suspend fun deleteAll() {
        weatherDao.deleteAll()
    }

    suspend fun insertAll(list: List<Data>) {
        weatherDao.insertAll(list)
    }

    suspend fun insert(data: Data) {
        weatherDao.insert(data)
    }

    fun getAll(): Flow<List<Data>> {
        return weatherDao.getAll()
    }

    fun getByCityName(city: String): Flow<List<Data>> {
        return weatherDao.getByCityName(city)
    }

}