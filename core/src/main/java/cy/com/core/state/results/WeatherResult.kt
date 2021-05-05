package cy.com.core.state.results

import cy.com.core.models.WeatherData
import cy.com.core.state.actions.WeatherAction
import cy.com.core.state.interfaces.Result

sealed class WeatherResult : Result {
    data class AddWeathers(val weatherData: List<WeatherData>) : WeatherResult()
    data class Error(val error: Throwable, val lastAction: WeatherAction) : WeatherResult()
    data class ActionWrapper(val action: WeatherAction) : WeatherResult()
}