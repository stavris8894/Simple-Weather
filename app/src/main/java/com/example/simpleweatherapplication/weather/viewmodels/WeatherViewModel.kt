package com.example.simpleweatherapplication.weather.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.application.SimpleWeatherApp
import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.state.BaseViewModel
import com.example.simpleweatherapplication.state.actions.WeatherAction
import com.example.simpleweatherapplication.state.results.WeatherResult
import com.example.simpleweatherapplication.state.viewstates.WeatherViewState
import com.example.simpleweatherapplication.utils.Event
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.utils.models.ResultWrapper
import com.example.simpleweatherapplication.weather.datasource.WeatherDatasource
import com.example.simpleweatherapplication.weather.interactor.WeatherInteractor
import com.example.simpleweatherapplication.weather.repositories.WeatherDatabaseRepository
import com.example.simpleweatherapplication.weather.repositories.WeatherRemoteRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import org.koin.ext.scope

class WeatherViewModel(
    private val weatherInteractor: WeatherInteractor,
) : BaseViewModel<WeatherViewState, WeatherAction, WeatherResult>() {

    override fun getInitialState(): WeatherViewState = WeatherViewState()

    override suspend fun handle(scope: LiveDataScope<WeatherResult>, action: WeatherAction) {
        when (action) {
            is WeatherAction.GetWeather -> {
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
//
//    private val _recycleViewItems = MutableLiveData<Event<List<RecyclerViewItem>>>()
//    val recycleViewItems: LiveData<Event<List<RecyclerViewItem>>> = _recycleViewItems
//
//    private val _showProgressBar = MutableLiveData<Event<Boolean>>()
//    val showProgressBar: LiveData<Event<Boolean>> = _showProgressBar
//
//    private val _showErrorMessage = MutableLiveData<Event<String>>()
//    val showErrorMessage: LiveData<Event<String>> = _showErrorMessage

    private val weatherDatasource = WeatherDatasource()

    init {
        dispatch(WeatherAction.GetWeather)
    }
//    fun getCityWeatherData(address: String, country: String? = null) {
//        _showProgressBar.value = Event(true)
//        viewModelScope.launch {
//            when (val weatherData = weatherRepository.getWeatherData(address, country)) {
//                is ResultWrapper.Success -> {
//                    weatherData.value?.data?.let {
//                        weatherDatabaseRepository.insert(it[0])
//                    }.also { _showProgressBar.value = Event(false) }
//                }
//                is ResultWrapper.NetworkError -> {
//                    _showErrorMessage.value = Event(SimpleWeatherApp.getString(R.string.network_error)).also { _showProgressBar.value = Event(false) }
//                }
//                is ResultWrapper.Error -> {
//                    weatherData.errorResponse?.let {
//                        _showErrorMessage.value = Event(it.getErrorMessage)
//                    }.also {
//                        _showProgressBar.value = Event(false)
//                    }
//                }
//            }
//        }
//    }

//    private fun getDataFromDao() {
//        viewModelScope.launch {
//            weatherDatabaseRepository.getAllFlow().collectLatest { it ->
//                _recycleViewItems.value = Event(weatherDatasource.convertDataToUIModel(it.sortedBy { it.cityName }))
//            }
//        }
//    }


//    fun refreshData() {
//        viewModelScope.launch {
//            val weatherData = weatherDatabaseRepository.getAllOneTime()
//            if (weatherData.isEmpty()) {
//                _showProgressBar.value = Event(false)
//                return@launch
//            }
//            weatherData.forEach {
//                getCityWeatherData(it.cityName, it.countryCode)
//            }
//        }
//    }
//
//    fun removeWeatherData(weatherData: WeatherData) {
//        viewModelScope.launch {
//            weatherDatabaseRepository.deleteWeather(weatherData)
//        }
//    }

    companion object {
        private val TAG = WeatherViewModel::class.java.simpleName
    }
}