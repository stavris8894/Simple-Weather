package com.example.simpleweatherapplication.room

import androidx.room.*
import com.example.simpleweatherapplication.models.WeatherData
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<WeatherData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherData: WeatherData)

    @Update
    suspend fun update(weatherData: WeatherData)

    @Query("SELECT * FROM WeatherData")
    fun getAll(): Flow<List<WeatherData>>

    @Query("DELETE FROM WeatherData")
    suspend fun deleteAll()

    @Query("SELECT * FROM WeatherData WHERE cityName=:city")
    fun getByCityName(city: String): Flow<WeatherData>

}