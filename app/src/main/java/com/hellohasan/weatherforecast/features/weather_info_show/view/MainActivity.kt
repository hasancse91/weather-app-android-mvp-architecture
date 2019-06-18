package com.hellohasan.weatherforecast.features.weather_info_show.view

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import com.hellohasan.weatherforecast.R

class MainActivity : AppCompatActivity(), MainActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCityListFetchSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCityListFetchFailure(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onWeatherInfoFetchSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onWeatherInfoFetchFailure(errorMessage: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
