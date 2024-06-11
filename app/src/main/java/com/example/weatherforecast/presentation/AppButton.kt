package com.example.weatherforecast.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.ui.theme.AppBlue
import com.example.weatherforecast.ui.theme.WeatherForecastTheme

@Composable
fun AppButton(text: String, onClick: () -> Unit)
{
    Button(
        colors = ButtonDefaults.buttonColors(AppBlue),
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(20.dp)
    )
    {
        Text(text = text)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AppButtonPreview()
{
    WeatherForecastTheme {
        AppButton("Allow Location", onClick = {})
    }
}