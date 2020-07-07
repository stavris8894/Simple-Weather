package com.example.simpleweatherapplication.room

import androidx.room.*
import com.example.simpleweatherapplication.models.Data
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Data>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: Data)

    @Update
    suspend fun update(data: Data)

    @Query("SELECT * FROM data")
    fun getAll(): Flow<List<Data>>

    @Query("DELETE FROM data")
    suspend fun deleteAll()

    @Query("SELECT * FROM data WHERE cityName=:city")
    fun getByCityName(city: String): Flow<List<Data>>

}