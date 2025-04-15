package com.example.notificationscomplete

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotificationScreen(context: Context, fromNotification: Boolean, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = { createBasicNotification(context) }
        ) {
            Text(text = "Basic Notification")
        }
        Button(
            onClick = { createCollapsableNotification(context) }
        ) {
            Text(text = "Collapsable Notification")
        }

        if(fromNotification) {
            Text(text = "From notification")
        }
    }
}