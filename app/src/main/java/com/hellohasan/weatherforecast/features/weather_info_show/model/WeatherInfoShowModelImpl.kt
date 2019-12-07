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

class WeatherInfoShowModelImpl(private val context: Context) : WeatherInfoShowModel {

    /**
     * Fetch city list from local. Yes, model only knows about data source. It doesn't know anything
     * about data validation, formatting or something about view.
     */
    override fun getCityList(callback: RequestCompleteListener<MutableList<City>>) {

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

            callback.onRequestSuccess(cityList) //let presenter know the city list

        } catch (e: IOException) {
           e.printStackTrace()
            callback.onRequestFailed(e.localizedMessage!!) //let presenter know about failure
        }

    }

    /**
     * Fetch weather information from remote server via HTTP network request.
     * Yes, model only knows about data source. Model's responsibility is fetch the data from source.
     * Model don't do anything about data formatting or checking any logic. Model will notify
     * presenter with raw data. Presenter will decide the logic and let know the view what should
     * show on the UI.
     */
    override fun getWeatherInformation(cityId: Int, callback: RequestCompleteListener<WeatherInfoResponse>) {

        val apiInterface: ApiInterface = RetrofitClient.client.create(ApiInterface::class.java)
        val call: Call<WeatherInfoResponse> = apiInterface.callApiForWeatherInfo(cityId)

        call.enqueue(object : Callback<WeatherInfoResponse> {

            // if retrofit network call success, this method will be triggered
            override fun onResponse(call: Call<WeatherInfoResponse>, response: Response<WeatherInfoResponse>) {
                if (response.body() != null)
                    callback.onRequestSuccess(response.body()!!) //let presenter know the weather information data
                else
                    callback.onRequestFailed(response.message()) //let presenter know about failure
            }

            // this method will be triggered if network call failed
            override fun onFailure(call: Call<WeatherInfoResponse>, t: Throwable) {
                callback.onRequestFailed(t.localizedMessage!!) //let presenter know about failure
            }

        })
    }

}