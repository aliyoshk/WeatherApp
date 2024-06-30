package com.example.weatherforecast

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weatherforecast.model.RouteTo
import com.example.weatherforecast.model.Screen
import com.example.weatherforecast.model.domain.WeatherDataRequest
import com.example.weatherforecast.presentation.component.AcceptDialog
import com.example.weatherforecast.presentation.Dashboard
import com.example.weatherforecast.presentation.Detail
import com.example.weatherforecast.presentation.LandingPage
import com.example.weatherforecast.presentation.component.DataScreen
import com.example.weatherforecast.presentation.component.OptionDialog
import com.example.weatherforecast.ui.theme.WeatherForecastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherForecastTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposableRoute()
                    //DataScreen(weatherDataRequest = WeatherDataRequest(cityName = "Lagos"))
                }
            }
        }
    }
}

@Composable
fun ComposableRoute()
{
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screen.LandingScreen.route
    )
    {
        composable(route = Screen.LandingScreen.route)
        {
            Greeting(navController, "")
        }
        composable(Screen.DashboardScreen.route)
        {
            Dashboard(navController)
        }
        composable(
            Screen.DetailsScreen.route + "/{bundle}",
            arguments = listOf(
                navArgument("data")
                {
                    type = NavType.StringType
                    defaultValue = "Empty value return"
                    nullable = true
                }
            )
        )
        {
            val data = it.arguments?.getString("bundle")!!
            Detail(navController, data = data)
        }
    }

}

@Composable
fun Greeting(navController: NavHostController ,name: String) {
    Text(text = "Hello $name!")

    val onGetStart = remember { mutableStateOf(false) }
    val allowLocation = remember { mutableStateOf(false) }
    val context = LocalContext.current

    LandingPage(onButtonClick = {

        onGetStart.value = true
        Toast.makeText(context, "Proceed button click", Toast.LENGTH_SHORT).show()
    } )

    if (onGetStart.value)
    {
        AcceptDialog(
            image = painterResource(id = R.drawable.ic_suncloud),
            title = "Location permission needed",
            message = "Please enable location permission to get more accurate weather information",
            onDismissRequest = { onGetStart.value = false },
            onButtonClick = { allowLocation.value = true; onGetStart.value = false}
        )
    }

    if (allowLocation.value)
    {
        allowLocation.value = false
        navController.navigate(Screen.DashboardScreen.route)
        //Dashboard()
    }
}

@Composable
fun Display()
{
    OptionDialog(
        image = painterResource(id = R.drawable.ic_suncloud),
        title = "Location permission needed",
        message = "Please enable location permission to get more accurate weather information",
        onProceed = {},
        onCancel = {},
        onDismissRequest = {},
        alignment = Alignment.Center,
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WeatherForecastTheme {
        val navController = rememberNavController()
        Greeting(navController,"Android")
    }
}