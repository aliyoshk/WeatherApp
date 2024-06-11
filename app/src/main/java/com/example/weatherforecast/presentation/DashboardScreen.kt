package com.example.weatherforecast.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.theme.AppBlue
import com.example.weatherforecast.ui.theme.WeatherForecastTheme

@Composable
fun Dashboard()
{
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DashboardTop()

        DashboardCard()

        Spacer(modifier = Modifier.height(15.dp))

        SliderCarousel(3)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Around the world",
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Spacer(Modifier.height(15.dp))

        LazyColumn {
            items(5)
            { index: Int ->
                DashboardCard()
            }
        }
    }
}

@Composable
fun DashboardTop() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
    ) {
        Column{
            Text("Hello Arian,",
                modifier = Modifier
                    .padding(bottom = 5.dp)
            )
            Text("Discover the weather")
        }

        Spacer(modifier = Modifier.weight(1f))

        Image(
            painterResource(R.drawable.ic_search),
            contentDescription = null,
            modifier = Modifier
                .background(Color.LightGray, CircleShape)
                .padding(5.dp)
                .size(30.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Image(
            painterResource(R.drawable.ic_world),
            contentDescription = null,
            modifier = Modifier
                .background(Color.LightGray, CircleShape)
                .padding(5.dp)
                .size(30.dp)
        )
    }
}

@Composable
fun DashboardCard()
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            .background(color = AppBlue, RoundedCornerShape(30.dp))
            .padding(20.dp)
    )
    {
        Row{
            Column{
                Text(
                    text = "Current location",
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    color = Color.White
                )
                Text(
                    text = "Dhaka",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painterResource(R.drawable.ic_suncloud),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 10.dp)
            )
        }

        Row(modifier = Modifier.padding(top = 20.dp))
        {
            Text(
                text = "Thunder",
                color = Color.White
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "20℃",
                color = Color.White,
            )
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DashboardPreview()
{
    WeatherForecastTheme {
        Dashboard()
    }
}