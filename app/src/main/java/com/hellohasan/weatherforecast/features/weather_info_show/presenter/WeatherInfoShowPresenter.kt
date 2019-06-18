package com.hellohasan.weatherforecast.features.weather_info_show.presenter

import com.hellohasan.weatherforecast.common.RequestCompleteListener
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherInfoResponse

interface WeatherInfoShowPresenter {
    fun fetchCityList(callback: RequestCompleteListener<MutableList<City>>)
    fun fetchWeatherInfo(cityId: Int, callback: RequestCompleteListener<WeatherInfoResponse>)
}