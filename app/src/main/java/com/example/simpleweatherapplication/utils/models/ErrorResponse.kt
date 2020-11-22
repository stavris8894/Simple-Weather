package com.example.simpleweatherapplication.utils.models

import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.application.SimpleWeatherApp
import okhttp3.Response

data class ErrorResponse(
    val error: String? = null,
    val status_code: Int? = null,
    val status_message: String? = null
) {
    constructor(error: Response) : this(status_code = error.code, status_message = error.message)

    val getErrorMessage: String
        get() {
            return status_message ?: error ?: SimpleWeatherApp.getString(R.string.unknown_error)
        }
}