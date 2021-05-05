package cy.com.core.room

import androidx.room.*
import cy.com.core.models.WeatherData
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<WeatherData>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weatherData: WeatherData)

    @Update
    suspend fun update(weatherData: WeatherData)

    @Delete
    suspend fun deleteWeather(weatherData: WeatherData)

    @Query("DELETE FROM WeatherData")
    suspend fun deleteAll()

    @Query("SELECT * FROM WeatherData WHERE cityName=:city")
    fun getByCityName(city: String): Flow<WeatherData>

    @Query("SELECT * FROM WeatherData")
    suspend fun getAllOneTime(): List<WeatherData>

    @Query("SELECT * FROM WeatherData")
    fun getAllFlow(): Flow<List<WeatherData>>

}