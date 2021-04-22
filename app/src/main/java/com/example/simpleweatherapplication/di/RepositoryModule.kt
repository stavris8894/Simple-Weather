package com.example.simpleweatherapplication.di

import com.example.simpleweatherapplication.weather.interactor.WeatherInteractor
import com.example.simpleweatherapplication.weather.repositories.WeatherDatabaseRepository
import com.example.simpleweatherapplication.weather.repositories.WeatherRemoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { WeatherRemoteRepository(get()) }
    factory { WeatherDatabaseRepository(get()) }

    //interactors
    factory { WeatherInteractor(get(), get()) }
}