package com.example.weatherforecast.model.Contract

import com.example.weatherforecast.model.Config.Urls.Companion.BASE_URL
import com.example.weatherforecast.model.domain.WeatherDataResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//interface IWeatherService {
//    @GET("endpoint/{id}")
//    suspend fun fetchData(@Path("id") id: String): WeatherDataResponse
//}

//const val URL = BASE_URL + "data/2.5/weather?q={0}&appid=a224befbe0282d095bb3e06e0cd2b568"
interface IWeatherService {
    @GET("data/2.5/weather")
    suspend fun fetchData(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String = "a224befbe0282d095bb3e06e0cd2b568"
    ): WeatherDataResponse
}