package cy.com.core.models

import com.google.gson.annotations.SerializedName


data class ApiWeatherResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("data")
    val data: List<WeatherData>
)