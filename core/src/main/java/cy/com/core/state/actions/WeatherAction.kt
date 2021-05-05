package cy.com.core.state.actions

import cy.com.core.models.WeatherData
import cy.com.core.state.interfaces.Action

sealed class WeatherAction : Action {
    data class FetchWeatherFromApi(val cityName: String, val shortName: String?) : WeatherAction()
    data class RemoveWeather(val weatherData: WeatherData) : WeatherAction()
    object GetWeather : WeatherAction()
    object UpdateWeathers : WeatherAction()
    object CleanError : WeatherAction()
}