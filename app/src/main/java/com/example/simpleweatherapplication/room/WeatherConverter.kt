package com.example.simpleweatherapplication.room

import androidx.room.TypeConverter
import com.example.simpleweatherapplication.models.Weather
import com.google.gson.Gson

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