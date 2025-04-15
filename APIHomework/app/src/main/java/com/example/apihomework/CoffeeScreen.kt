package com.example.apihomework

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CoffeeScreen(viewModel: CoffeeViewModel = viewModel()) {
    val imageUrl by viewModel.coffeeImageUrl.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when {
            loading -> {
                CircularProgressIndicator()
            }

            error != null -> {
                Text(error!!, color = Color.Red, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.fetchCoffee() }) {
                    Text("Retry")
                }
            }

            imageUrl != null -> {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = "Coffee Image",
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.fetchCoffee() }) {
                    Text("Load New Coffee")
                }
            }
        }
    }
}
