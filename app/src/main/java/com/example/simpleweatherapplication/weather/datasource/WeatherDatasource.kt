package com.example.simpleweatherapplication.weather.datasource

import com.example.simpleweatherapplication.R
import com.example.simpleweatherapplication.application.SimpleWeatherApp
import com.example.simpleweatherapplication.models.WeatherData
import com.example.simpleweatherapplication.ui_data.*
import com.example.simpleweatherapplication.utils.extensions.toCelsius
import com.example.simpleweatherapplication.utils.interfaces.RecyclerViewItem
import com.example.simpleweatherapplication.utils.interfaces.ViewHolderId

enum class WeatherActionsItem : ViewHolderId {
    DONE_BUTTON,
    LABEL,
    SHOW_DETAILS
}

class WeatherDatasource {

    fun convertDataToUIModel(list: List<WeatherData>): ArrayList<RecyclerViewItem> {
        val uiData: ArrayList<RecyclerViewItem> = arrayListOf()
        list.forEach {
            uiData.add(
                WeatherCardViewData(
                    id = WeatherActionsItem.SHOW_DETAILS,
                    data = it
                )
            )
        }
        return uiData
    }

    fun showWeatherDetails(weatherData: WeatherData): ArrayList<RecyclerViewItem> {
        val detailsData: ArrayList<RecyclerViewItem> = arrayListOf()
        detailsData.apply {
            add(
                HeaderWeatherDetailsViewData(
                    id = WeatherActionsItem.LABEL,
                    data = TitleSubtitleDataModel(
                        weatherData.cityName, weatherData.temp.toString().toCelsius()
                    )
                )
            )
            add(
                WeatherDetailsViewData(
                    id = WeatherActionsItem.LABEL,
                    data = TitleSubtitleDataModel(
                        SimpleWeatherApp.getString(R.string.date_time), weatherData.lastObservationTimeString
                    )
                )
            )
            add(
                WeatherDetailsViewData(
                    id = WeatherActionsItem.LABEL,
                    data = TitleSubtitleDataModel(SimpleWeatherApp.getString(R.string.sunset), weatherData.sunset)
                )
            )
            add(
                WeatherDetailsViewData(
                    id = WeatherActionsItem.LABEL,
                    data = TitleSubtitleDataModel(SimpleWeatherApp.getString(R.string.timezone), weatherData.timezone)
                )
            )
            add(
                WeatherDetailsViewData(
                    id = WeatherActionsItem.LABEL,
                    data = TitleSubtitleDataModel(SimpleWeatherApp.getString(R.string.countryCode), weatherData.countryCode)
                )
            )
            add(
                WeatherDetailsViewData(
                    id = WeatherActionsItem.LABEL,
                    data = TitleSubtitleDataModel(
                        SimpleWeatherApp.getString(R.string.longitude), weatherData.lon
                    )
                )
            )
            add(
                WeatherDetailsViewData(
                    id = WeatherActionsItem.LABEL,
                    data = TitleSubtitleDataModel(SimpleWeatherApp.getString(R.string.latitude), weatherData.lat)
                )
            )
            add(
                WeatherDetailsViewData(
                    id = WeatherActionsItem.LABEL,
                    data = TitleSubtitleDataModel(SimpleWeatherApp.getString(R.string.last_update), weatherData.lastObservationTimeString)
                )
            )
            add(
                ButtonViewData(
                    id = WeatherActionsItem.DONE_BUTTON,
                    title = SimpleWeatherApp.getString(R.string.done)
                )
            )
        }
        return detailsData
    }
}