package com.hellohasan.weatherforecast.features.weather_info_show.model

import android.content.Context
import com.google.gson.GsonBuilder
import com.hellohasan.weatherforecast.common.RequestCompleteListener
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.City
import com.hellohasan.weatherforecast.features.weather_info_show.model.data_class.WeatherInfoResponse
import com.hellohasan.weatherforecast.network.ApiInterface
import com.hellohasan.weatherforecast.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import com.google.gson.reflect.TypeToken

class WeatherInfoShowModelImpl : WeatherInfoShowModel {

    override fun getCityList(context: Context, callback: RequestCompleteListener<MutableList<City>>) {

        try {
            val stream = context.assets.open("city_list.json")

            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val tContents  = String(buffer)

            val groupListType = object : TypeToken<ArrayList<City>>() {}.type
            val gson = GsonBuilder().create()
            val cityList: MutableList<City> = gson.fromJson(tContents, groupListType)

            callback.onRequestSuccess(cityList)

        } catch (e: IOException) {
           e.printStackTrace()
            callback.onRequestFailed(e.localizedMessage)
        }

    }

    override fun getWeatherInformation(cityId: Int, callback: RequestCompleteListener<WeatherInfoResponse>) {

        val apiInterface: ApiInterface = RetrofitClient.client.create(ApiInterface::class.java)
        val call: Call<WeatherInfoResponse> = apiInterface.callApiForWeatherInfo(cityId)

        call.enqueue(object : Callback<WeatherInfoResponse> {

            override fun onResponse(call: Call<WeatherInfoResponse>, response: Response<WeatherInfoResponse>) {
                if (response.body() != null)
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