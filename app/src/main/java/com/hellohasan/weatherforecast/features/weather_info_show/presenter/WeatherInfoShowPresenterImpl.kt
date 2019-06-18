package com.hellohasan.weatherforecast.features.weather_info_show.presenter

import com.hellohasan.weatherforecast.common.RequestCompleteListener
import com.hellohasan.weatherforecast.features.weather_info_show.model.WeatherInfoShowModelImpl
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherInfoResponse
import com.hellohasan.weatherforecast.features.weather_info_show.view.MainActivityView

class WeatherInfoShowPresenterImpl(mainActivityView: MainActivityView): WeatherInfoShowPresenter {

    private val weatherInfoShowModel = WeatherInfoShowModelImpl()

    override fun fetchCityList(callback: RequestCompleteListener<MutableList<City>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchWeatherInfo(cityId: Int, callback: RequestCompleteListener<WeatherInfoResponse>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}