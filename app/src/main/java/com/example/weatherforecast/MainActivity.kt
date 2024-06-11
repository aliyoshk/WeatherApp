package com.example.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.weatherforecast.model.RouteTo
import com.example.weatherforecast.model.Screen
import com.example.weatherforecast.presentation.AcceptDialog
import com.example.weatherforecast.presentation.Dashboard
import com.example.weatherforecast.presentation.LandingPage
import com.example.weatherforecast.presentation.OptionDialog
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
        composable(RouteTo.Dashboard.name)
        {
            Dashboard()
        }
    }

}

@Composable
fun Greeting(navController: NavHostController ,name: String) {
    Text(text = "Hello $name!")

    val onGetStart = remember { mutableStateOf(false) }
    val allowLocation = remember { mutableStateOf(false) }

    LandingPage(onButtonClick = { onGetStart.value = true } )

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

//    if (allowLocation.value) {
//        OptionDialog(
//            image = painterResource(id = R.drawable.ic_suncloud),
//            title = "Location permission needed",
//            message = "Please enable location permission to get more accurate weather information",
//            onProceed = {},
//            onCancel = {},
//            onDismissRequest = {},
//            alignment = Alignment.Center,
//            textAlign = TextAlign.Center
//        )
//    }
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
        var navController = rememberNavController()
        Greeting(navController,"Android")
    }
}