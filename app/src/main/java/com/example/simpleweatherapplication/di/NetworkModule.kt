package com.example.simpleweatherapplication.di

import com.example.simpleweatherapplication.network.RestClient
import org.koin.dsl.module


val networkModule = module {
    single { RestClient.instance }
}