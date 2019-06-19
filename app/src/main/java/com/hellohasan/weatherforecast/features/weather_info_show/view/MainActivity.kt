package com.hellohasan.weatherforecast.features.weather_info_show.view

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hellohasan.weatherforecast.R
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherDataModel
import com.hellohasan.weatherforecast.features.weather_info_show.presenter.WeatherInfoShowPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_input_part.*
import kotlinx.android.synthetic.main.layout_sunrise_sunset.*
import kotlinx.android.synthetic.main.layout_weather_additional_info.*
import kotlinx.android.synthetic.main.layout_weather_basic_info.*

class MainActivity : AppCompatActivity(), MainActivityView {

    private val weatherInfoShowPresenter = WeatherInfoShowPresenterImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weatherInfoShowPresenter.fetchCityList(this)

        btn_view_weather.setOnClickListener {
            weatherInfoShowPresenter.fetchWeatherInfo(1185241)
        }
    }

    override fun handleProgressBarVisibility(visibility: Int) {
        progressBar?.visibility = visibility
    }

    override fun onCityListFetchSuccess(cityList: MutableList<City>) {
        Toast.makeText(this, cityList.size, Toast.LENGTH_SHORT).show()
    }

    override fun onCityListFetchFailure(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    override fun onWeatherInfoFetchSuccess(weatherDataModel: WeatherDataModel) {
        output_group.visibility = View.VISIBLE
        tv_error_message.visibility = View.GONE

        tv_date_time?.text = weatherDataModel.dateTime
        tv_temperature?.text = weatherDataModel.temperature
        tv_city_country?.text = weatherDataModel.cityAndCountry
        Glide.with(this).load(weatherDataModel.weatherConditionIconUrl).into(iv_weather_condition)
        tv_weather_condition?.text = weatherDataModel.weatherConditionIconDescription

        tv_humidity_value?.text = weatherDataModel.humidity
        tv_pressure_value?.text = weatherDataModel.pressure
        tv_visibility_value?.text = weatherDataModel.visibility

        tv_sunrise_time?.text = weatherDataModel.sunrise
        tv_sunset_time?.text = weatherDataModel.sunset
    }

    override fun onWeatherInfoFetchFailure(errorMessage: String) {
        output_group.visibility = View.GONE
        tv_error_message.visibility = View.VISIBLE
        tv_error_message.text = errorMessage
    }
}
