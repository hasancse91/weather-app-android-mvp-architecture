package com.hellohasan.weatherforecast.features.weather_info_show.view

interface MainActivityView {
    fun onCityListFetchSuccess()
    fun onCityListFetchFailure(errorMessage: String)
    fun onWeatherInfoFetchSuccess()
    fun onWeatherInfoFetchFailure(errorMessage: String)
}