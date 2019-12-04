package com.hellohasan.weatherforecast.features.weather_info_show.presenter

import android.view.View
import com.hellohasan.weatherforecast.features.weather_info_show.model.WeatherInfoShowModel
import com.hellohasan.weatherforecast.common.RequestCompleteListener
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherDataModel
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherInfoResponse
import com.hellohasan.weatherforecast.features.weather_info_show.view.MainActivityView
import com.hellohasan.weatherforecast.kelvinToCelsius
import com.hellohasan.weatherforecast.unixTimestampToDateTimeString
import com.hellohasan.weatherforecast.unixTimestampToTimeString

class WeatherInfoShowPresenterImpl(
        private var view: MainActivityView?,
        private val weatherInfoShowModel: WeatherInfoShowModel) : WeatherInfoShowPresenter {

    override fun fetchCityList() {
        weatherInfoShowModel.getCityList(object : RequestCompleteListener<MutableList<City>> {

            override fun onRequestSuccess(data: MutableList<City>) {
                view?.onCityListFetchSuccess(data)
            }

            override fun onRequestFailed(errorMessage: String) {
                view?.onCityListFetchFailure(errorMessage)
            }
        })
    }

    override fun fetchWeatherInfo(cityId: Int) {

        view?.handleProgressBarVisibility(View.VISIBLE)

        weatherInfoShowModel.getWeatherInformation(cityId, object : RequestCompleteListener<WeatherInfoResponse> {

            override fun onRequestSuccess(data: WeatherInfoResponse) {

                view?.handleProgressBarVisibility(View.GONE)

                // data formatting to show on UI
                val weatherDataModel = WeatherDataModel(
                    dateTime = data.dt.unixTimestampToDateTimeString(),
                    temperature = data.main.temp.kelvinToCelsius().toString(),
                    cityAndCountry = "${data.name}, ${data.sys.country}",
                    weatherConditionIconUrl = "http://openweathermap.org/img/w/${data.weather[0].icon}.png",
                    weatherConditionIconDescription = data.weather[0].description,
                    humidity = "${data.main.humidity}%",
                    pressure = "${data.main.pressure} mBar",
                    visibility = "${data.visibility/1000.0} KM",
                    sunrise = data.sys.sunrise.unixTimestampToTimeString(),
                    sunset = data.sys.sunset.unixTimestampToTimeString()
                )

                view?.onWeatherInfoFetchSuccess(weatherDataModel)
            }

            override fun onRequestFailed(errorMessage: String) {
                view?.handleProgressBarVisibility(View.GONE)

                view?.onWeatherInfoFetchFailure(errorMessage)
            }
        })
    }

    override fun detachView() {
        view = null
    }
}