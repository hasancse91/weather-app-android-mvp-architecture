package com.hellohasan.weatherforecast.features.weather_info_show.view

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.hellohasan.weatherforecast.R
import com.hellohasan.weatherforecast.utils.convertToListOfCityName
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

    private lateinit var model: WeatherInfoShowModel
    private lateinit var presenter: WeatherInfoShowPresenter

    private var cityList: MutableList<City> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // initialize model and presenter
        model = WeatherInfoShowModelImpl(applicationContext)
        presenter = WeatherInfoShowPresenterImpl(this, model)

        // call for fetching city list
        presenter.fetchCityList()


        btn_view_weather.setOnClickListener {
            output_group.visibility = View.GONE

            val spinnerSelectedItemPos = spinner.selectedItemPosition

            // fetch weather info of specific city
            presenter.fetchWeatherInfo(cityList[spinnerSelectedItemPos].id)
        }
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    /**
     * Activity doesn't know when should progress bar visible or hide. It only knows
     * how to show/hide it.
     * Presenter will decide the logic of progress bar visibility.
     * This method will be triggered by presenter when needed.
     */
    override fun handleProgressBarVisibility(visibility: Int) {
        progressBar?.visibility = visibility
    }

    /**
     * This method will be triggered when city list successfully fetched.
     * From where this list will be come? From local db or network call or from somewhere else?
     * Activity/View doesn't know and doesn't care anything about it. Activity only knows how to
     * show the city list on the UI and listen the click event of the Spinner.
     * Model knows about the data source of city list.
     */
    override fun onCityListFetchSuccess(cityList: MutableList<City>) {
        this.cityList = cityList

        val arrayAdapter = ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                cityList.convertToListOfCityName()
        )

        spinner.adapter = arrayAdapter
    }

    /**
     * This method will triggered if city list fetching process failed
     */
    override fun onCityListFetchFailure(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    /**
     * This method will triggered when weather information successfully fetched.
     * Activity/View doesn't know anything about the data source of weather API.
     * Only model knows about the data source of weather API.
     */
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

    /**
     * This method will triggered if weather information fetching process failed
     */
    override fun onWeatherInfoFetchFailure(errorMessage: String) {
        output_group.visibility = View.GONE
        tv_error_message.visibility = View.VISIBLE
        tv_error_message.text = errorMessage
    }
}