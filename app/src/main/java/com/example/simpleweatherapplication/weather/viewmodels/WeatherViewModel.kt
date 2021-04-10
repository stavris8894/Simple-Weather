package com.example.simpleweatherapplication.weather.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.application.SimpleWeatherApp
import com.example.simpleweatherapplication.utils.Event
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.utils.models.ResultWrapper
import com.example.simpleweatherapplication.weather.datasource.WeatherDatasource
import com.example.simpleweatherapplication.weather.repositories.WeatherDatabaseRepository
import com.example.simpleweatherapplication.weather.repositories.WeatherRemoteRepository
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WeatherViewModel(
    application: Application,
    private val weatherRepository: WeatherRemoteRepository,
    private val weatherDatabaseRepository: WeatherDatabaseRepository
) : AndroidViewModel(application) {

    private val _recycleViewItems = MutableLiveData<Event<ArrayList<RecyclerViewItem>>>()
    val recycleViewItems: LiveData<Event<ArrayList<RecyclerViewItem>>> = _recycleViewItems

    private val _showProgressBar = MutableLiveData<Event<Boolean>>()
    val showProgressBar: LiveData<Event<Boolean>> = _showProgressBar

    private val _showErrorMessage = MutableLiveData<Event<String>>()
    val showErrorMessage: LiveData<Event<String>> = _showErrorMessage

    private val _showCityDetails = MutableLiveData<Event<ArrayList<RecyclerViewItem>>>()
    val showCityDetails: LiveData<Event<ArrayList<RecyclerViewItem>>> = _showCityDetails

    private val weatherDatasource = WeatherDatasource()

    fun getCityWeatherData(address: String, country: String? = null) {
        _showProgressBar.value = Event(true)
        viewModelScope.launch {
            when (val weatherData = weatherRepository.getWeatherData(address, country)) {
                is ResultWrapper.Success -> {
                    weatherData.value?.data?.let {
                        weatherDatabaseRepository.insert(it[0])
                    }.also { _showProgressBar.value = Event(false) }
                }
                is ResultWrapper.NetworkError -> {
                    _showErrorMessage.value = Event(SimpleWeatherApp.getString(R.string.network_error)).also { _showProgressBar.value = Event(false) }
                }
                is ResultWrapper.Error -> {
                    _showErrorMessage.value = Event(weatherData.errorResponse.getErrorMessage).also { _showProgressBar.value = Event(false) }
                }
            }
        }
    }

    private fun getDataFromDao() {
        viewModelScope.launch {
            weatherDatabaseRepository.getAll().collect { it ->
                _recycleViewItems.value = Event(weatherDatasource.convertDataToUIModel(it.sortedBy { it.cityName }))
            }
        }
    }


    fun refreshData() {
        viewModelScope.launch {
            weatherDatabaseRepository.getAll().collectLatest { it ->
                it.forEach {
                    getCityWeatherData(it.cityName, it.countryCode)
                }
                cancel()
            }
        }
    }

    fun showWeatherDetails(id: String) {
        viewModelScope.launch {
            weatherDatabaseRepository.getByCityName(id).collectLatest {
                val showWeatherDetails = weatherDatasource.showWeatherDetails(it)
                _showCityDetails.value = Event(showWeatherDetails)
                cancel()
            }
        }
    }

    init {
        getDataFromDao()
    }

    companion object {
        private val TAG = WeatherViewModel::class.java.simpleName
    }
}