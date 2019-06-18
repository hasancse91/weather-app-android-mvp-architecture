package com.hellohasan.weatherforecast.features.weather_info_show.model

import com.hellohasan.weatherforecast.common.RequestCompleteListener
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherInfoResponse
import com.hellohasan.weatherforecast.network.ApiInterface
import com.hellohasan.weatherforecast.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherInfoShowModelImpl: WeatherInfoShowModel {

    private val OPEN_WEATHER_APP_ID = "d450a4a574372bd12f2fa308bf3cf15a"

    override fun getCityList(callback: RequestCompleteListener<MutableList<City>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWeatherInformation(cityId: Int, callback: RequestCompleteListener<WeatherInfoResponse>) {

        val apiInterface: ApiInterface = RetrofitClient.client.create(ApiInterface::class.java)
        val call: Call<WeatherInfoResponse> = apiInterface.callApiForWeatherInfo(cityId, OPEN_WEATHER_APP_ID)

        call.enqueue(object: Callback<WeatherInfoResponse>{

            override fun onResponse(call: Call<WeatherInfoResponse>, response: Response<WeatherInfoResponse>) {
                if (response.body()!=null)
                    callback.onRequestSuccess(response.body()!!)
                else
                    callback.onRequestFailed(response.message())
            }

            override fun onFailure(call: Call<WeatherInfoResponse>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage)
            }

        })
    }

}