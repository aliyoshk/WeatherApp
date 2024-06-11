package com.example.weatherforecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.example.weatherforecast.presentation.AcceptDialog
import com.example.weatherforecast.presentation.OptionDialog
import com.example.weatherforecast.ui.theme.WeatherForecastTheme
import kotlinx.coroutines.Delay

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
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")

    val allow = remember { mutableStateOf(false) }
    AcceptDialog(
        image = painterResource(id = R.drawable.ic_suncloud),
        title = "Location permission needed",
        message = "Please enable location permission to get more accurate weather information",
        onDismissRequest = { allow.value = false },
        onButtonClick = { allow.value = true }
    )

    if (allow.value) {
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
        Greeting("Android")
    }
}