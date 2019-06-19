package com.hellohasan.weatherforecast.network

import com.google.gson.GsonBuilder
import com.hellohasan.weatherforecast.BuildConfig
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

object RetrofitClient {

    private const val BASE_URL = "http://api.openweathermap.org/data/2.5/"

    private var retrofit: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    val client: Retrofit
        get() {
            if (retrofit == null) {
                synchronized(Retrofit::class.java) {
                    if (retrofit == null) {

                        val httpClient = OkHttpClient.Builder()

                        // for pretty log of HTTP request-response
                        httpClient.addInterceptor(
                                LoggingInterceptor.Builder()
                                        .loggable(BuildConfig.DEBUG)
                                        .setLevel(Level.BASIC)
                                        .log(Platform.INFO)
                                        .request("LOG")
                                        .response("LOG")
                                        .executor(Executors.newSingleThreadExecutor())
                                        .build())

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