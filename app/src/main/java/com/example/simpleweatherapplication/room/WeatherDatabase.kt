package com.example.simpleweatherapplication.room

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.simpleweatherapplication.models.Data
import com.example.simpleweatherapplication.models.Weather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Data::class], version = 1, exportSchema = false)
@TypeConverters(WeatherConverter::class)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {

        private var instance: WeatherDatabase? = null

        private const val DATABASE_NAME = "weather_db"

        fun getInstance(context: Context): WeatherDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private val roomCallBack = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                CoroutineScope(Dispatchers.IO).launch {
                    instance?.apply {
//                        weatherDao().deleteAll()
                    }
                }
            }
        }

        private fun buildDatabase(context: Context): WeatherDatabase {
            return Room.databaseBuilder(
                context, WeatherDatabase::class.java,
                DATABASE_NAME
            ).addCallback(roomCallBack).build()
        }
    }


}
