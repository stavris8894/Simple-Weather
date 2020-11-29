package com.example.simpleweatherapplication.weather.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.application.SimpleWeatherApp
import com.example.simpleweatherapplication.models.Data
import com.example.simpleweatherapplication.ui_data.ButtonViewData
import com.example.simpleweatherapplication.ui_data.HeaderWeatherDetailsViewData
import com.example.simpleweatherapplication.ui_data.WeatherCardViewData
import com.example.simpleweatherapplication.ui_data.WeatherDetailsViewData
import com.example.simpleweatherapplication.utils.Event
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.utils.extensions.toCelsius
import com.example.simpleweatherapplication.utils.models.ResultWrapper
import com.example.simpleweatherapplication.weather.repositories.WeatherDatabaseRepository
import com.example.simpleweatherapplication.weather.repositories.WeatherRemoteRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

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

    private val _dismissCityDetails = MutableLiveData<Event<Boolean>>()
    val dismissCityDetails: LiveData<Event<Boolean>> = _dismissCityDetails

    fun getCityWeatherData(address: String, country: String? = null) {
        _showProgressBar.value = Event(true)
        viewModelScope.async {
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
        viewModelScope.async {
            weatherDatabaseRepository.getAll().collect { it ->
                _recycleViewItems.value = Event(convertDataToUIModel(it.sortedBy { it.cityName }))
            }
        }
    }


    fun refreshData() {
        viewModelScope.async {
            weatherDatabaseRepository.getAll().collectLatest { it ->
                it.forEach {
                    getCityWeatherData(it.cityName, it.countryCode)
                }
                this.cancel()
            }
        }
    }

    private fun convertDataToUIModel(list: List<Data>): ArrayList<RecyclerViewItem> {
        val uiData: ArrayList<RecyclerViewItem> = arrayListOf()
        list.forEach {
            uiData.add(WeatherCardViewData(it.cityName, it.cityName, it.countryCode, it.temp.toString(), it.weather.icon, listener = ::onClick))
        }
        return uiData
    }

    private fun onClick(id: String) {
        viewModelScope.async {
            weatherDatabaseRepository.getByCityName(id).collectLatest {
                val detailsData: ArrayList<RecyclerViewItem> = arrayListOf()
                detailsData.add(HeaderWeatherDetailsViewData(it.cityName, it.temp.toString().toCelsius()))
                detailsData.add(WeatherDetailsViewData(SimpleWeatherApp.getString(R.string.date_time), it.lastObservationTimeString))
                detailsData.add(WeatherDetailsViewData(SimpleWeatherApp.getString(R.string.sunset), it.sunset))
                detailsData.add(WeatherDetailsViewData(SimpleWeatherApp.getString(R.string.timezone), it.timezone))
                detailsData.add(WeatherDetailsViewData(SimpleWeatherApp.getString(R.string.countryCode), it.countryCode))
                detailsData.add(WeatherDetailsViewData(SimpleWeatherApp.getString(R.string.longitude), it.lon))
                detailsData.add(WeatherDetailsViewData(SimpleWeatherApp.getString(R.string.latitude), it.lat))
                detailsData.add(ButtonViewData(SimpleWeatherApp.getString(R.string.done)) {
                    _dismissCityDetails.value = Event(true)
                })
                _showCityDetails.value = Event(detailsData)
                this.cancel()
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