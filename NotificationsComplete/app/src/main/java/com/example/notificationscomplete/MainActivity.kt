package com.example.notificationscomplete

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.notificationscomplete.ui.theme.NotificationsCompleteTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val fromNotification = intent?.getBooleanExtra("from_notification", false)

        setContent {
            NotificationsCompleteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RequestNotificationPermissions()
                    NotificationScreen(
                        context = this,
                        fromNotification = fromNotification ?: false,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}