package com.example.simpleweatherapplication.utils.models

import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T) : ResultWrapper<T>()
    data class Error(val errorResponse: ErrorResponse) : ResultWrapper<Nothing>()
    object NetworkError : ResultWrapper<Nothing>()
}


suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> Response<T>): ResultWrapper<T?> {
    return withContext(dispatcher) {
        val response = apiCall.invoke()
        if (response.isSuccessful) {
            when {
                response.code() == 200 -> {
                    ResultWrapper.Success(response.body())
                }
                response.code() == 204 -> ResultWrapper.Error(ErrorResponse(status_code = 204, status_message = "Cannot be found in the API db"))
                else -> ResultWrapper.NetworkError
            }
        } else {
            if (response.errorBody() != null) {
                try {
                    Gson().fromJson(response.errorBody()?.toString(), ErrorResponse::class.java).let {
                        ResultWrapper.Error(it)
                    }
                } catch (t: Throwable) {
                    ResultWrapper.Error(ErrorResponse(response.raw()))
                }
            } else {
                ResultWrapper.Error(ErrorResponse(response.raw()))
            }
        }
    }
}