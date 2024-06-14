package com.example.weatherforecast.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherforecast.R
import com.example.weatherforecast.ui.theme.AppBlue
import com.example.weatherforecast.ui.theme.WeatherForecastTheme

@Composable
fun OptionDialog(image: Painter, title: String?, message: String,
               onProceed: () -> Unit, onCancel: () -> Unit,
               onDismissRequest: () -> Unit, alignment: Alignment, textAlign: TextAlign
)
{
    AlertDialog(
        icon =
        {
            //Icon(image, contentDescription = "Iconic sample")
            Box(
                contentAlignment = alignment,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(AppBlue)
            )
            {
                Icon(
                    painter = image,
                    contentDescription = "Iconic sample",
                    modifier = Modifier.size(50.dp),
                    tint = Color.White
                )
            }
        },
        title = {
            if (title != null)
                Text(text = title, textAlign = textAlign)
        },
        text = { Text(text = message, textAlign = textAlign) },
        confirmButton =
        {
            TextButton(onClick = { onProceed() })
            {
                Text(text = "Proceed")
            }
        },
        dismissButton =
        {
            TextButton(onClick = { onCancel() })
            {
                Text(text = "Cancel")
            }
        },
        onDismissRequest = { onDismissRequest() }
    )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun OptionDialogPreview()
{
    WeatherForecastTheme {
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