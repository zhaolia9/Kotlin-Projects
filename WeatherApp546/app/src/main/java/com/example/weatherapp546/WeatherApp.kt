package com.example.weatherapp546

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed class Weather(
    val temperature: String,
    val conditionImage: Int,
    val description: String
) {
    object Sunny : Weather("80º F", R.drawable.ic_sunny, "Sunny")
    object Cloudy : Weather("50º F", R.drawable.ic_cloudy, "Cloudy")
    object Rainy : Weather("45º F", R.drawable.ic_rainy, "Rainy")
}

@Preview(showBackground = true)
@Composable
fun WeatherApp() {
    val currentWeather = remember { mutableStateOf<Weather>(Weather.Sunny) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(currentWeather.value.conditionImage),
            contentDescription = "Weather Condition",
            modifier = Modifier.size(120.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = currentWeather.value.temperature,
            fontSize = 48.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(onClick = {
                currentWeather.value = Weather.Sunny
            }) {
                Text("New York")
            }

            Button(onClick = {
                currentWeather.value = Weather.Rainy
            }) {
                Text("London")
            }

            Button(onClick = {
                currentWeather.value = Weather.Cloudy
            }) {
                Text("Tokyo")
            }
        }
    }
}



