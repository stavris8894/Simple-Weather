package com.example.simpleweatherapplication.application

import android.app.Application
import androidx.annotation.StringRes
import com.example.simpleweatherapplication.BuildConfig
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.di.networkModule
import com.example.simpleweatherapplication.di.repositoryModule
import com.example.simpleweatherapplication.di.viewModelModule
import com.google.android.libraries.places.api.Places
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

class SimpleWeatherApp : Application() {

    companion object {
        private lateinit var instance: SimpleWeatherApp

        fun getString(@StringRes resId: Int): String {
            return instance.resources.getString(resId)
        }

        fun getString(@StringRes resId: Int, arg: String): String {
            return instance.resources.getString(resId, arg)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Places.initialize(applicationContext, BuildConfig.GOOGLE_KEY, Locale.ENGLISH)
        startKoin {
            androidContext(this@SimpleWeatherApp)
            modules(listOf(repositoryModule, viewModelModule, networkModule))
        }

    }
}