package com.example.weatherforecast.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecast.model.Factory.RetrofitInstance
import com.example.weatherforecast.model.domain.Weather
import com.example.weatherforecast.model.domain.WeatherDataRequest
import com.example.weatherforecast.model.domain.WeatherDataResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val _dataList = MutableStateFlow<List<Weather>>(emptyList())
    val dataList: StateFlow<List<Weather>> get() = _dataList

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> get() = _error

    private  val _weatherResponse = MutableStateFlow<WeatherDataResponse?>(null)
    val weatherResponse: StateFlow<WeatherDataResponse?> get() = _weatherResponse


    fun fetchData(weatherDataRequest: WeatherDataRequest) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                val response = RetrofitInstance.api.fetchData(
                    cityName = weatherDataRequest.cityName
                )
                _dataList.value = response.weather
                _weatherResponse.value = response
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}