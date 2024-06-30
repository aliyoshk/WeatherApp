package com.example.weatherforecast.model.Factory

import com.example.weatherforecast.model.Config.Urls
import com.example.weatherforecast.model.Contract.IWeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: IWeatherService by lazy {
        Retrofit.Builder()
            .baseUrl(Urls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IWeatherService::class.java)
    }
}