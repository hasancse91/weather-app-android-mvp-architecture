package com.hellohasan.weatherforecast.features.weather_info_show.view

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hellohasan.weatherforecast.R
import com.hellohasan.weatherforecast.convertToListOfCityName
import com.hellohasan.weatherforecast.features.weather_info_show.model.WeatherInfoShowModel
import com.hellohasan.weatherforecast.features.weather_info_show.model.WeatherInfoShowModelImpl
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherDataModel
import com.hellohasan.weatherforecast.features.weather_info_show.presenter.WeatherInfoShowPresenter
import com.hellohasan.weatherforecast.features.weather_info_show.presenter.WeatherInfoShowPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_input_part.*
import kotlinx.android.synthetic.main.layout_sunrise_sunset.*
import kotlinx.android.synthetic.main.layout_weather_additional_info.*
import kotlinx.android.synthetic.main.layout_weather_basic_info.*

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var weatherInfoShowModel: WeatherInfoShowModel
    private lateinit var weatherInfoShowPresenter: WeatherInfoShowPresenter

    private var cityList: MutableList<City> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize model and presenter
        weatherInfoShowModel = WeatherInfoShowModelImpl(applicationContext)
        weatherInfoShowPresenter = WeatherInfoShowPresenterImpl(this, weatherInfoShowModel)

        // call for fetching city list
        weatherInfoShowPresenter.fetchCityList()


        btn_view_weather.setOnClickListener {
            output_group.visibility = View.GONE

            val spinnerSelectedItemPos = spinner.selectedItemPosition

            // fetch weather info of specific city
            weatherInfoShowPresenter.fetchWeatherInfo(cityList[spinnerSelectedItemPos].id)
        }
    }

    override fun onDestroy() {
        weatherInfoShowPresenter.detachView()
        super.onDestroy()
    }

    override fun handleProgressBarVisibility(visibility: Int) {
        progressBar?.visibility = visibility
    }

    override fun onCityListFetchSuccess(cityList: MutableList<City>) {
        this.cityList = cityList

        val arrayAdapter = ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                cityList.convertToListOfCityName()
        )

        spinner.adapter = arrayAdapter
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
