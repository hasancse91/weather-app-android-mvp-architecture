package com.hellohasan.weatherforecast.features.weather_info_show.presenter

import android.content.Context
import android.view.View
import com.hellohasan.weatherforecast.common.RequestCompleteListener
import com.hellohasan.weatherforecast.features.weather_info_show.model.WeatherInfoShowModelImpl
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherDataModel
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherInfoResponse
import com.hellohasan.weatherforecast.features.weather_info_show.view.MainActivityView
import com.hellohasan.weatherforecast.kelvinToCelsius
import com.hellohasan.weatherforecast.unixTimestampToDateTimeString
import com.hellohasan.weatherforecast.unixTimestampToTimeString

class WeatherInfoShowPresenterImpl(private val view: MainActivityView) : WeatherInfoShowPresenter {

    private val weatherInfoShowModel = WeatherInfoShowModelImpl()

    override fun fetchCityList(context: Context) {
        weatherInfoShowModel.getCityList(context, object : RequestCompleteListener<MutableList<City>> {

            override fun onRequestSuccess(data: MutableList<City>) {
                view.onCityListFetchSuccess(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                view.onCityListFetchFailure(errorMessage)
            }
        })
    }

    override fun fetchWeatherInfo(cityId: Int) {

        view.handleProgressBarVisibility(View.VISIBLE)

        weatherInfoShowModel.getWeatherInformation(cityId, object : RequestCompleteListener<WeatherInfoResponse> {

            override fun onRequestSuccess(data: WeatherInfoResponse) {

                view.handleProgressBarVisibility(View.GONE)

                // data formatting to show on UI
                val weatherDataModel = WeatherDataModel()
                weatherDataModel.dateTime = data.dt.unixTimestampToDateTimeString()
                weatherDataModel.temperature = data.main.temp.kelvinToCelsius().toString()
                weatherDataModel.cityAndCountry = "${data.name}, ${data.sys.country}"
                weatherDataModel.weatherConditionIconUrl = "http://openweathermap.org/img/w/${data.weather[0].icon}.png"
                weatherDataModel.weatherConditionIconDescription = data.weather[0].description
                weatherDataModel.humidity = "${data.main.humidity}%"
                weatherDataModel.pressure = "${data.main.pressure} mBar"
                weatherDataModel.visibility = "${data.visibility} KM"
                weatherDataModel.sunrise = data.sys.sunrise.unixTimestampToTimeString()
                weatherDataModel.sunset = data.sys.sunset.unixTimestampToTimeString()

                view.onWeatherInfoFetchSuccess(weatherDataModel)
            }

            override fun onRequestFailed(errorMessage: String) {
                view.handleProgressBarVisibility(View.GONE)

                view.onWeatherInfoFetchFailure(errorMessage)
            }
        })
    }
}