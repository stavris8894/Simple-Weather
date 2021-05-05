package cy.com.core.state.results

import cy.com.core.models.WeatherData
import cy.com.core.state.actions.WeatherDetailsAction
import cy.com.core.state.interfaces.Result

sealed class WeatherDetailsResult : Result {
    data class ShowWeatherDetails(val weatherData: WeatherData) : WeatherDetailsResult()
    data class ActionWrapper(val action: WeatherDetailsAction) : WeatherDetailsResult()
    data class Error(val error: Throwable, val lastAction: WeatherDetailsAction) : WeatherDetailsResult()
}