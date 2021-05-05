package cy.com.core.state.actions

import cy.com.core.state.interfaces.Action

sealed class WeatherDetailsAction : Action {
    data class GetWeatherDetails(val id: String) : WeatherDetailsAction()
    object CleanError : WeatherDetailsAction()
}