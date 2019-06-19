package com.hellohasan.weatherforecast.features.weather_info_show.view

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import com.hellohasan.weatherforecast.R
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherInfoResponse
import com.hellohasan.weatherforecast.features.weather_info_show.presenter.WeatherInfoShowPresenterImpl
import kotlinx.android.synthetic.main.layout_input_part.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity(), MainActivityView {

    private val weatherInfoShowPresenter = WeatherInfoShowPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_view_weather.setOnClickListener {
            weatherInfoShowPresenter.fetchWeatherInfo(1185241)
        }
    }

    override fun onCityListFetchSuccess(cityList: MutableList<City>) {

    }

    override fun onCityListFetchFailure(errorMessage: String) {

    }

    override fun onWeatherInfoFetchSuccess(weatherInfoResponse: WeatherInfoResponse) {

    }

    override fun onWeatherInfoFetchFailure(errorMessage: String) {

    }

}
