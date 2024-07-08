package com.example.weatherforecast.presentation.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.weatherforecast.model.domain.WeatherDataResponse
import com.google.gson.Gson
import com.google.gson.JsonSerializationContext

class MemoryManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun saveWeatherData(key: String, value: WeatherDataResponse){
        val editor = sharedPreferences.edit()
        editor.putString(key, Gson().toJson(value))
        editor.apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun getWeatherData(key: String) : WeatherDataResponse?{
        val response = sharedPreferences.getString(key, null)
        return if (response != null) {
            Gson().fromJson(response, WeatherDataResponse::class.java)
        }  else null
    }
}