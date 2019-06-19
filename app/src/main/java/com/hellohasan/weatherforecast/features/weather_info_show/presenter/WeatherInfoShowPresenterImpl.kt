package com.hellohasan.weatherforecast.features.weather_info_show.presenter

import com.hellohasan.weatherforecast.common.RequestCompleteListener
import com.hellohasan.weatherforecast.features.weather_info_show.model.WeatherInfoShowModelImpl
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherInfoResponse
import com.hellohasan.weatherforecast.features.weather_info_show.view.MainActivityView

class WeatherInfoShowPresenterImpl(private val mainActivityView: MainActivityView) : WeatherInfoShowPresenter {

    private val weatherInfoShowModel = WeatherInfoShowModelImpl()

    override fun fetchCityList() {
        weatherInfoShowModel.getCityList(object : RequestCompleteListener<MutableList<City>> {
            override fun onRequestSuccess(data: MutableList<City>) {
                mainActivityView.onCityListFetchSuccess(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                mainActivityView.onCityListFetchFailure(errorMessage)
            }
        })
    }

    override fun fetchWeatherInfo(cityId: Int) {
        weatherInfoShowModel.getWeatherInformation(cityId, object : RequestCompleteListener<WeatherInfoResponse> {
            override fun onRequestSuccess(data: WeatherInfoResponse) {
                mainActivityView.onWeatherInfoFetchSuccess(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                mainActivityView.onWeatherInfoFetchFailure(errorMessage)
            }
        })
    }
}