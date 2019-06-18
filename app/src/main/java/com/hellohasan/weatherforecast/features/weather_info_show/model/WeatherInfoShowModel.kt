package com.hellohasan.weatherforecast.features.weather_info_show.model

interface WeatherInfoShowModel {
    fun getCityList()
    fun getWeatherInformation(cityId: Int)
}