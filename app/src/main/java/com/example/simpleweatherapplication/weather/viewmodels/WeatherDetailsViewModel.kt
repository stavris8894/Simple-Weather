package com.example.simpleweatherapplication.weather.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.simpleweatherapplication.utils.Event
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.weather.datasource.WeatherDatasource
import com.example.simpleweatherapplication.weather.repositories.WeatherDatabaseRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WeatherDetailsViewModel(
    application: Application,
    private val weatherDatabaseRepository: WeatherDatabaseRepository
) : AndroidViewModel(application) {

    private val _showCityDetails = MutableLiveData<Event<ArrayList<RecyclerViewItem>>>()
    val showCityDetails: LiveData<Event<ArrayList<RecyclerViewItem>>> = _showCityDetails

    private val weatherDatasource = WeatherDatasource()

    fun showWeatherDetails(id: String) {
        viewModelScope.launch {
            weatherDatabaseRepository.getByCityName(id).collectLatest {
                val showWeatherDetails = weatherDatasource.showWeatherDetails(it)
                _showCityDetails.value = Event(showWeatherDetails)
            }
        }
    }
}