package com.hellohasan.weatherforecast.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/weather")
    fun callApiForWeatherInfo(@Query("id") cityId: Int, @Query("appid") appId: String)
}