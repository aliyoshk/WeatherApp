package com.example.weatherforecast.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherforecast.R
import com.example.weatherforecast.presentation.component.SliderCarousel
import com.example.weatherforecast.ui.theme.AppBlue
import com.example.weatherforecast.ui.theme.LightPurple
import com.example.weatherforecast.ui.theme.PurpleGrey40
import com.example.weatherforecast.ui.theme.WeatherForecastTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Detail()
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .padding(start = 30.dp, top = 30.dp, end = 30.dp)
        )
        {
            Image(
                painter = painterResource(R.drawable.ic_details),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .background(AppBlue, CircleShape)
                    .padding(12.dp),
            )

            Spacer(Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .width(100.dp)
                    .background(color = LightPurple, RoundedCornerShape(60.dp)),
                horizontalArrangement = Arrangement.End
            )
            {
                Text(text = "Today",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(end = 4.dp)
                )

                Image(
                    painter = painterResource(R.drawable.ic_arrow_down),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .background(AppBlue, CircleShape)
                        .padding(8.dp),
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        val pagerState = rememberPagerState()
        HorizontalPager(
            pageCount = 3 ,
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 52.dp)
        )
        {
            DetailCard(it, pagerState)
            //FallingPoleCard(it, pagerState)
        }

        Spacer(Modifier.height(20.dp))

        SliderCarousel(3)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailCard(page: Int, pagerState: PagerState)
{
    val pageOffset = calculateCurrentOffsetForPage(page, pagerState)
    val rotation = pageOffset * -30f  // Apply a rotation of -30 degrees

    Box(contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .graphicsLayer
            {
                rotationZ = rotation
                //transformOrigin = TransformOrigin(0.2f, 0.5f)
            }
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(350.dp)
                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                .background(color = AppBlue, RoundedCornerShape(30.dp))
                .padding(0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Image(
                painter = painterResource(R.drawable.ic_suncloud),
                contentDescription = null,
                modifier = Modifier
                    .size(170.dp)
            )

            Spacer(Modifier.height(15.dp))

            Text("Rampura,Dhaka",
                style = TextStyle(color = Color.White)
                )

            Spacer(Modifier.height(5.dp))

            Text(
                "20℃",
                style = TextStyle(color = Color.White),
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(5.dp))

            Text("Softly coldy",
                style = TextStyle(color = Color.White))
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun calculateCurrentOffsetForPage(page: Int, pagerState: PagerState): Float {
    val currentPage = pagerState.currentPage
    val offset = (pagerState.currentPageOffsetFraction)
    return (page - currentPage) + offset
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DetailPreview()
{
    WeatherForecastTheme {
        Detail()
    }
}