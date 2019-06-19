package com.hellohasan.weatherforecast.features.weather_info_show.view

import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherInfoResponse

interface MainActivityView {
    fun onCityListFetchSuccess(cityList: MutableList<City>)
    fun onCityListFetchFailure(errorMessage: String)
    fun onWeatherInfoFetchSuccess(weatherInfoResponse: WeatherInfoResponse)
    fun onWeatherInfoFetchFailure(errorMessage: String)
}