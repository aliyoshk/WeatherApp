package com.example.weatherforecast.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.ui.theme.AppBlue

@Composable
fun SliderCarousel(number: Int, currentPage: Int = -1)
{
    Row(
        modifier = Modifier
            .padding(bottom = 8.dp), // Space between dots and text
        horizontalArrangement = Arrangement.Center
    )
    {
        repeat(number)
        {
            val color = if (currentPage == it) AppBlue else Color.Gray
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
            if (it < 2) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}