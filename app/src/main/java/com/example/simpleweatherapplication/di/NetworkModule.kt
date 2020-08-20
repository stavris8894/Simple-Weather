package com.example.simpleweatherapplication.di

import android.os.Build.HOST
import com.datatheorem.android.trustkit.TrustKit
import com.example.simpleweatherapplication.BuildConfig
import com.example.simpleweatherapplication.network.ApiWebServices
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import java.util.concurrent.TimeUnit

private const val API_URL = "https://api.weatherbit.io/v2.0/"

val networkModule = module {

    single { okHttp() }

    single {
        retrofit()
    }

    single {
        get<Retrofit>().create(ApiWebServices::class.java)
    }

}

private fun okHttp() = OkHttpClient.Builder()
    .readTimeout(120, TimeUnit.SECONDS)
    .writeTimeout(120, TimeUnit.SECONDS)
    .connectTimeout(120, TimeUnit.SECONDS)
    .addInterceptor { chain ->
        val url = chain.request().url.newBuilder()
            .addQueryParameter("key", BuildConfig.API_KEY)
            .build()
        val request = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .url(url)
            .build()
        chain.proceed(request)
    }.apply {
        if (BuildConfig.DEBUG) {
            try {
                val hostName = URL(API_URL).host
                sslSocketFactory(
                    TrustKit.getInstance().getSSLSocketFactory(hostName),
                    TrustKit.getInstance().getTrustManager(hostName)
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }.build()

private fun retrofit() = Retrofit.Builder()
    .callFactory(OkHttpClient.Builder().build())
    .baseUrl(API_URL)
    .client(okHttp())
    .addConverterFactory(GsonConverterFactory.create())
    .build()