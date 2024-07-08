package com.example.weatherforecast.presentation

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecast.R
import com.example.weatherforecast.model.Screen
import com.example.weatherforecast.model.domain.WeatherDataRequest
import com.example.weatherforecast.presentation.component.AcceptDialog
import com.example.weatherforecast.presentation.component.SliderCarousel
import com.example.weatherforecast.presentation.utils.MemoryManager
import com.example.weatherforecast.ui.theme.AppBlue
import com.example.weatherforecast.ui.theme.WeatherForecastTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Dashboard(navController: NavHostController)
{
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        val pagerState = rememberPagerState()
        val countries = listOf("Dhaka", "California", "Beijing", "Moscow", "Nigeria")
        val weather = listOf("Thunder", "Clear", "Mostly sunny", "Cloudy", "Shower")
        val deg = listOf("20℃", "5℃", "6℃", "11℃", "-04℃")

        var cityName by remember { mutableStateOf("") }
        val preferencesManager = remember { MemoryManager(context) }
        val savedData by remember { mutableStateOf(preferencesManager.getWeatherData("myData")) }

        DashboardTop(onSearch = { cityName = it })

        if (cityName.isNotEmpty() || savedData != null)
        {
            if (savedData != null)
            {
                val res = savedData?.let { "${ it.name }, ${ it.id }, ${ it.wind }" }
                Toast.makeText(context, res, Toast.LENGTH_SHORT).show()
            }

            PerformApiCall(cityName)

            val viewModel: WeatherViewModel = viewModel()
            val weatherResponse = viewModel.weatherResponse.collectAsState().value ?: savedData

            if (weatherResponse != null)
            {

                preferencesManager.saveWeatherData("myData", weatherResponse)

                HorizontalPager(pageCount = weatherResponse.name.count(), state = pagerState)
                { page ->

                    DashboardCard(
                        weatherResponse.name,
                        weatherResponse.weather[0].description,
                        "${weatherResponse.wind.deg}℃",
                        {}
                    )
                }

                LaunchedEffect(pagerState)
                {
                    // Collect from the a snapshotFlow reading the currentPage
                    snapshotFlow {
                        pagerState.currentPage
                    }.collect { page ->
                        // Do something with each page change, for example:
                        // viewModel.sendPageSelectedEvent(page)
                        val mes = "Page change Page changed to ${page.plus(1)}"
                        Toast.makeText(context, mes, Toast.LENGTH_SHORT).show()
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                SliderCarousel(weatherResponse.name.count(), pagerState.currentPage)
            }

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

            LazyColumn() {
                items(5)
                { index ->
                    DashboardCard(
                        countries[index], weather[index], deg[index],
                        onClick = {
                            val data = countries[index] + " " + weather[index] + " " + deg[index]
                            navController.navigate(
                                Screen.DetailsScreen.route + "/${data}"
                            )
//                        {
//                            popUpTo(Screen.LandingScreen.route)
//                        }
                        }
                    )
                }
            }
        }
        else
            NoRecord()
    }
}

@Composable
fun NoRecord()
{
    val context = LocalContext.current
    val preferencesManager = remember { MemoryManager(context) }

    Column(Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.ic_error),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .background(Color.Red, CircleShape)
        )

        Spacer(Modifier.height(50.dp))

        Text(
            text = "No saved record, Click on search",
            color = Color.Red,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardTop(onSearch: (String) -> Unit) {
    var searchClicked by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }

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
                .clickable { searchClicked = !searchClicked }
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

        if (searchClicked)
        {
            Dialog(onDismissRequest = { searchClicked = false },
                properties = DialogProperties(dismissOnClickOutside = true, dismissOnBackPress = true)
            )
            {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(30.dp))
                        .padding(bottom = 30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    OutlinedTextField(
                        label = { Text("Enter city") },
                        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
                        value = text,
                        onValueChange = { text = it },
                        modifier = Modifier
                            .weight(1f)
                            .alpha(if (!searchClicked) 0f else 1f)
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {
                            onSearch(text)
                            searchClicked = false
                        } ),
                    )
                }
            }
        }
    }
}


@Composable
fun DashboardCard(country: String, whether: String, degree: String, onClick: () -> Unit)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            .background(color = AppBlue, RoundedCornerShape(30.dp))
            .padding(20.dp)
            .clickable { onClick() }
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
                    text = country,
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
                text = whether,
                color = Color.White
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = degree,
                color = Color.White,
            )
        }
    }
}

@Composable
private fun PerformApiCall(cityName: String)
{
    val viewModel: WeatherViewModel = viewModel()
    val isLoading = viewModel.isLoading.collectAsState().value
    val error = viewModel.error.collectAsState().value
    val weatherResponse = viewModel.weatherResponse.collectAsState().value
    var isShowError by remember { mutableStateOf(false) }

    LaunchedEffect(cityName)
    {
        if (cityName.isNotEmpty())
            viewModel.fetchData(WeatherDataRequest(cityName = cityName))
    }

    if (isLoading)
    {
        CircularProgressIndicator()
        Text(text = "Please wait...")
    }
    else if (error != null)
    {
        isShowError = true
        Text(text = "Error: $error", color = MaterialTheme.colorScheme.error)
    }
    else
    {
        weatherResponse?.let { response ->
            Column {
                Text(text = "City: ${response.name}")
                Text(text = "Temperature: ${response.main.temp}")
                // Display more weather information...
            }
        }
    }

    if (isShowError)
    {
        AcceptDialog(
            painterResource(R.drawable.ic_error),
            title = "Error",
            message = error ?: "Unknown error",
            onDismissRequest = {isShowError = false},
            onButtonClick = {
                isShowError = false
                viewModel.clearError() },
            buttonText = "Dismiss"
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DashboardPreview()
{
    WeatherForecastTheme {
        val navController = rememberNavController()
        Dashboard(navController)
    }
}