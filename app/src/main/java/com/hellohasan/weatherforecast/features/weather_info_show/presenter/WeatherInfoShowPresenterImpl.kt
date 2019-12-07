package com.hellohasan.weatherforecast.features.weather_info_show.presenter

import android.view.View
import com.hellohasan.weatherforecast.features.weather_info_show.model.WeatherInfoShowModel
import com.hellohasan.weatherforecast.common.RequestCompleteListener
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherDataModel
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherInfoResponse
import com.hellohasan.weatherforecast.features.weather_info_show.view.MainActivityView
import com.hellohasan.weatherforecast.utils.kelvinToCelsius
import com.hellohasan.weatherforecast.utils.unixTimestampToDateTimeString
import com.hellohasan.weatherforecast.utils.unixTimestampToTimeString

class WeatherInfoShowPresenterImpl(
        private var view: MainActivityView?,
        private val model: WeatherInfoShowModel) : WeatherInfoShowPresenter {

    override fun fetchCityList() {
        // call model's method for city list
        model.getCityList(object : RequestCompleteListener<MutableList<City>> {

            // if model successfully fetch the data from 'somewhere', this method will be called
            override fun onRequestSuccess(data: MutableList<City>) {
                view?.onCityListFetchSuccess(data) //let view know the formatted city list data
            }

            // if model failed to fetch data then this method will be called
            override fun onRequestFailed(errorMessage: String) {
                view?.onCityListFetchFailure(errorMessage) //let view know about failure
            }
        })
    }

    override fun fetchWeatherInfo(cityId: Int) {

        view?.handleProgressBarVisibility(View.VISIBLE) // let view know about progress bar visibility

        // call model's method for weather information
        model.getWeatherInformation(cityId, object : RequestCompleteListener<WeatherInfoResponse> {

            // if model successfully fetch the data from 'somewhere', this method will be called
            override fun onRequestSuccess(data: WeatherInfoResponse) {

                view?.handleProgressBarVisibility(View.GONE) // let view know about progress bar visibility

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

                view?.onWeatherInfoFetchSuccess(weatherDataModel) //let view know the formatted weather data
            }

            // if model failed to fetch data then this method will be called
            override fun onRequestFailed(errorMessage: String) {
                view?.handleProgressBarVisibility(View.GONE) // let view know about progress bar visibility

                view?.onWeatherInfoFetchFailure(errorMessage) //let view know about failure
            }
        })
    }

    override fun detachView() {
        view = null
    }
}