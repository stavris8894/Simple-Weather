package cy.com.core.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import cy.com.core.models.Weather

class WeatherConverter {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromWeatherToJson(weather: Weather): String {
            return Gson().toJson(weather)
        }

        @TypeConverter
        @JvmStatic
        fun fromJsonToWeather(weatherJson: String): Weather {
            return Gson().fromJson(weatherJson, Weather::class.java)
        }
    }

}