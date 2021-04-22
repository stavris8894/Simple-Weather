package com.example.simpleweatherapplication.weather.viewmodels

import androidx.lifecycle.LiveDataScope
import com.example.simpleweatherapplication.state.BaseViewModel
import com.example.simpleweatherapplication.state.actions.WeatherAction
import com.example.simpleweatherapplication.state.results.WeatherResult
import com.example.simpleweatherapplication.state.viewstates.WeatherViewState
import com.example.simpleweatherapplication.weather.datasource.WeatherDatasource
import com.example.simpleweatherapplication.weather.interactor.WeatherInteractor
import kotlinx.coroutines.flow.collectLatest

class WeatherViewModel(
    private val weatherInteractor: WeatherInteractor,
) : BaseViewModel<WeatherViewState, WeatherAction, WeatherResult>() {

    private val weatherDatasource = WeatherDatasource()

    override fun getInitialState(): WeatherViewState = WeatherViewState()

    override suspend fun handle(scope: LiveDataScope<WeatherResult>, action: WeatherAction) {
        when (action) {
            is WeatherAction.GetWeather -> {
                cancelPreviousDispatches(action)
                weatherInteractor.getDataFromDao().collectLatest {
                    scope.emit(WeatherResult.AddWeathers(it))
                }
            }
            is WeatherAction.FetchWeatherFromApi -> {
                weatherInteractor.getCityWeatherData(action.cityName, action.shortName)
            }
            is WeatherAction.UpdateWeathers -> {
                weatherInteractor.updateWeathers()
            }
            is WeatherAction.RemoveWeather -> {
                weatherInteractor.removeWeatherData(action.weatherData)
            }
            else -> {
                scope.emit(WeatherResult.ActionWrapper(action))
            }
        }
    }

    override fun handleError(action: WeatherAction, error: Throwable): WeatherResult {
        return WeatherResult.Error(error, action)
    }

    override fun reduce(result: WeatherResult): WeatherViewState {
        return when (result) {
            is WeatherResult.ActionWrapper -> when (val action = result.action) {
                else -> state
            }
            is WeatherResult.AddWeathers -> state.copy(
                recycleViewItems = weatherDatasource.convertDataToUIModel(result.weatherData),
                showProgressBar = false
            )
            is WeatherResult.Error -> state.copy(error = result.error)
        }
    }

    init {
        dispatch(WeatherAction.GetWeather)
    }

    companion object {
        private val TAG = WeatherViewModel::class.java.simpleName
    }
}