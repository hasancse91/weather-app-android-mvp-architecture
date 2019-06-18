package com.hellohasan.weatherforecast.network

import com.google.gson.GsonBuilder
import com.hellohasan.weatherforecast.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://api.openweathermap.org/data/2.5"

    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    val client: Retrofit
        get() {
            if (retrofit == null) {
                synchronized(Retrofit::class.java) {
                    if (retrofit == null) {
                        val httpClient = OkHttpClient.Builder()

                        if (BuildConfig.DEBUG) {
                            val loggingInterceptor = HttpLoggingInterceptor()
                            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                            httpClient.addInterceptor(loggingInterceptor)
                        }

                        val client = httpClient.build()

                        retrofit = Retrofit.Builder()
                                .baseUrl(BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create(gson))
                                .client(client)
                                .build()
                    }
                }

            }
            return retrofit!!
        }
}