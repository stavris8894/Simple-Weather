package com.example.simpleweatherapplication.network

import com.datatheorem.android.trustkit.TrustKit
import com.example.simpleweatherapplication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit

object RestClient {
    private const val API_URL = "https://api.weatherbit.io/v2.0/"

    val instance = createInstance()

    private fun createInstance(): ApiWebServices {
        val okHttpClientBuilder = OkHttpClient.Builder().also { builder ->
            builder.readTimeout(60, TimeUnit.SECONDS)
            builder.writeTimeout(60, TimeUnit.SECONDS)
            builder.connectTimeout(60, TimeUnit.SECONDS)
            builder.addInterceptor { chain ->
                val url = chain.request().url.newBuilder()
                    .addQueryParameter("key", BuildConfig.API_KEY)
                    .build()
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .url(url)
                    .build()
                return@addInterceptor chain.proceed(request)
            }
        }
        okHttpClientBuilder.isLoggingInterceptorEnabled = BuildConfig.DEBUG
        okHttpClientBuilder.enableSSLPinning = BuildConfig.DEBUG.not()
        return Retrofit.Builder().also { builder ->
            builder.client(okHttpClientBuilder.build())
            builder.baseUrl(API_URL)
            builder.addConverterFactory(GsonConverterFactory.create())
        }.build().create(ApiWebServices::class.java)
    }

    //extension to enable debug http logging
    var OkHttpClient.Builder.isLoggingInterceptorEnabled: Boolean
        get() = interceptors().find { it is HttpLoggingInterceptor } != null
        set(value) {
            if (value) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(loggingInterceptor)
            }
        }

    //extension to enable ssl pinning with trust kit
    var OkHttpClient.Builder.enableSSLPinning: Boolean
        get() = false
        set(value) {
            if (value) {
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
        }

}