package com.hellohasan.weatherforecast.features.weather_info_show.model.data_class

data class WeatherDataModel(
        val dateTime: String = "",
        val temperature: Double = 0.0,
        val cityAndCountry: String = "",
        val weatherConditionIconUrl: String = "",
        val weatherConditionIconDescription: String = "",
        val humidity: String = "",
        val pressure: String = "",
        val visibility: String = "",
        val sunrise: String = "",
        val sunset: String = ""
)