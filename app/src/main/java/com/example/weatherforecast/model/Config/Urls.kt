package com.example.weatherforecast.model.Config

class Urls {
    companion object {
        const val BASE_URL = "https://api.openweathermap.org/"
        const val URL = BASE_URL + "data/2.5/weather?q={0}&appid=a224befbe0282d095bb3e06e0cd2b568"
    }
}