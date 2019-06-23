package com.hellohasan.weatherforecast.features.weather_info_show.presenter

import android.content.Context

interface WeatherInfoShowPresenter {
    fun fetchCityList(context: Context)
    fun fetchWeatherInfo(cityId: Int)
    fun detachView()
}