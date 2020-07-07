package com.example.simpleweatherapplication.utils.extensions

fun String.toImgUrl(): String {
    return "https://www.weatherbit.io/static/img/icons/$this.png"
}

fun String.toCelsius(): String {
    return "$this \u2103"
}