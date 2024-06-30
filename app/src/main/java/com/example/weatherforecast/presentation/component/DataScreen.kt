package com.example.weatherforecast.presentation.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherforecast.model.domain.WeatherDataRequest
import com.example.weatherforecast.presentation.WeatherViewModel

@Composable
fun DataScreen(viewModel: WeatherViewModel = viewModel(), weatherDataRequest: WeatherDataRequest)
{
    val isLoading = viewModel.isLoading.collectAsState().value
    val dataList = viewModel.dataList.collectAsState().value
    val error = viewModel.error.collectAsState().value
    val weatherDataResponse = viewModel.weatherResponse.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchData(weatherDataRequest)
    }

    when {
        isLoading -> {
            CircularProgressIndicator()
        }
        error != null -> {
            Text(text = "Error: $error")
        }
        else -> {
            LazyColumn {
                items(dataList) { item ->
                    Text(text = item.main, style = MaterialTheme.typography.bodyLarge)
                }
            }
            println(weatherDataResponse)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DataScreenPreview() {
    DataScreen(weatherDataRequest = WeatherDataRequest(cityName = "New York"))
}