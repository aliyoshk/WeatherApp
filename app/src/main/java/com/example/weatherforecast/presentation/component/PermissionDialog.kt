package com.example.weatherforecast.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.theme.AppBlue
import com.example.weatherforecast.ui.theme.WeatherForecastTheme


@Composable
fun AcceptDialog(image: Painter, title: String?, message: String,
                 onDismissRequest: () -> Unit,
                 onButtonClick: () -> Unit)
{
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White, shape = RoundedCornerShape(30.dp))
                .padding(bottom = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Spacer(modifier = Modifier.height(40.dp))

            Image(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .background(color = AppBlue, shape = CircleShape)
                    .padding(20.dp)
                    .clip(CircleShape)
                    .size(80.dp),
                painter = painterResource(R.drawable.ic_suncloud),
                contentDescription = null,
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (title != null){
                Text(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(bottom = 10.dp),
                    text = title,
                    style = TextStyle(
                        textAlign = TextAlign.Center, fontWeight = FontWeight.Bold
                    ),
                    maxLines = 3,
                )
            }
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                text = message,
                style = TextStyle(
                    textAlign = TextAlign.Center
                ),
                maxLines = 3,
            )
            Spacer(modifier = Modifier.height(30.dp))

            AppButton("Allow location", onClick = { onButtonClick() })
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AcceptDialogPreview()
{
    WeatherForecastTheme {
        AcceptDialog(
            painterResource(R.drawable.ic_suncloud),
            title = "Location permission needed",
            message = "Please enable location permission to get more accurate weather information",
            onDismissRequest = {}, onButtonClick = {})
    }
}