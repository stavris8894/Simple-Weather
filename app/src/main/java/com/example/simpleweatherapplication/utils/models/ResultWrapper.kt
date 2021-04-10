package com.example.simpleweatherapplication.utils.models

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.simpleweatherapplication.application.SimpleWeatherApp
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.net.ssl.SSLHandshakeException

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val errorResponse: ErrorResponse?) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}


suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.Error(errorResponse)
                }
                is SSLHandshakeException -> {
                    LocalBroadcastManager.getInstance(SimpleWeatherApp()).sendBroadcast(Intent(INTENT_FILTER))
                    ResultWrapper.Error(null)
                }
                else -> {
                    ResultWrapper.NetworkError
                }
            }
        }
    }
}

const val INTENT_FILTER = "handshake-error"


private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        Gson().fromJson(throwable.response()?.errorBody()?.toString(), ErrorResponse::class.java)
    } catch (exception: Exception) {
        null
    }
}