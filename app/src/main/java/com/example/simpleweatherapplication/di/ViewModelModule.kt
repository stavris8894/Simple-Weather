package com.example.simpleweatherapplication.di

import com.example.simpleweatherapplication.weather.viewmodels.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { WeatherViewModel(get(), get(), get()) }
}