package com.example.simpleweatherapplication.weather.viewmodels

import androidx.lifecycle.LiveDataScope
import cy.com.core.state.BaseViewModel
import cy.com.core.state.actions.WeatherDetailsAction
import cy.com.core.state.results.WeatherDetailsResult
import cy.com.core.state.viewstates.WeatherDetailsViewState
import com.example.simpleweatherapplication.weather.datasource.WeatherDatasource
import com.example.simpleweatherapplication.weather.interactor.WeatherInteractor
import kotlinx.coroutines.flow.collectLatest

class WeatherDetailsViewModel(
    private val weatherInteractor: WeatherInteractor
) : BaseViewModel<WeatherDetailsViewState, WeatherDetailsAction, WeatherDetailsResult>() {

    private val weatherDatasource = WeatherDatasource()

    override fun getInitialState(): WeatherDetailsViewState = WeatherDetailsViewState()

    override fun handleError(action: WeatherDetailsAction, error: Throwable): WeatherDetailsResult {
        return WeatherDetailsResult.Error(error, action)
    }

    override suspend fun handle(scope: LiveDataScope<WeatherDetailsResult>, action: WeatherDetailsAction) {
        when (action) {
            is WeatherDetailsAction.GetWeatherDetails -> {
                weatherInteractor.getWeatherDetailsById(action.id).collectLatest {
                    scope.emit(WeatherDetailsResult.ShowWeatherDetails(it))
                }
            }
            else -> scope.emit(WeatherDetailsResult.ActionWrapper(action))
        }
    }

    override fun reduce(result: WeatherDetailsResult): WeatherDetailsViewState {
        return when (result) {
            is WeatherDetailsResult.ActionWrapper -> when (val action = result.action) {
                else -> state
            }
            is WeatherDetailsResult.Error -> state.copy(error = result.error)
            is WeatherDetailsResult.ShowWeatherDetails -> state.copy(
                weatherData = weatherDatasource.showWeatherDetails(result.weatherData)
            )
        }
    }
}