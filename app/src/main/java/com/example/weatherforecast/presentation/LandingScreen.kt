package com.example.weatherforecast.presentation

import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.theme.AppBlue
import com.example.weatherforecast.ui.theme.WeatherForecastTheme


@Composable
fun LandingPage()
{
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Image(
            painter = painterResource(id = R.drawable.bg_landing),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(10.dp)
                .background(
                    color = Color.White.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(top = 10.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            )
            {

                SliderCarousel(3)

                Text(
                    text = "Expore global map of wind, weather, and ocean conditions",
                    color = Color.Black,
                    fontSize = 20.sp,
                    style = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.padding(top = 15.dp)
                )

                Text(
                    text = "Planing your trip become more easier with ideate weather app. " +
                            "you can instantly see the whole word weather within few second",
                    style = TextStyle(textAlign = TextAlign.Center),
                    modifier = Modifier.padding(top = 10.dp, bottom = 60.dp)
                )

                AppButton("Get started", onClick = {})

                Row{
                    Text("Already have an account ? ")
                    Text("Log in",
                        color = AppBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun SliderCarousel(number: Int)
{
    Row(
        modifier = Modifier
            .padding(bottom = 8.dp), // Space between dots and text
        horizontalArrangement = Arrangement.Center
    )
    {
        repeat(number)
        {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(Color.Gray)
            )
            if (it < 2) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppLandingPage()
{
    WeatherForecastTheme {
        LandingPage()
    }
}
