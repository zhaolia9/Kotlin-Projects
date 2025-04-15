package com.example.notificationscomplete

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.O)
fun createBasicNotification(context: Context) {
    val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
            as NotificationManager
    val channelId = "ChannelId"
    getNotificationChannel(context, notificationManager, channelId)

    val notificationBuilder = Notification.Builder(context, channelId)
        .setContentTitle("Notification Title")
        .setContentText("Notification Content")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setContentIntent(setPendingIntent(context))

    notificationManager.notify(1, notificationBuilder.build())
}

@RequiresApi(Build.VERSION_CODES.O)
fun createCollapsableNotification(context: Context) {
    val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
            as NotificationManager
    val channelId = "ChannelId"
    getNotificationChannel(context, notificationManager, channelId)

    val style = Notification.InboxStyle()
    style.apply {
        addLine("Message 1")
        addLine("Message 2")
        addLine("Message 3")
        addLine("Message 4")
        addLine("Message 5")
        addLine("Message 6")
        setSummaryText("+5 more items...")
    }

    val notificationBuilder = Notification.Builder(context, channelId)
        .setContentTitle("Collapsable Notification")
        .setContentText("Collapsable Content")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setStyle(style)
        .setContentIntent(setPendingIntent(context))

    notificationManager.notify(1, notificationBuilder.build())
}

private fun setPendingIntent(context: Context): PendingIntent {
    val intent = Intent(context, MainActivity::class.java).apply {

        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or
                Intent.FLAG_ACTIVITY_CLEAR_TOP

        putExtra("from_notification", true)
    }

    return PendingIntent.getActivity(
        context,
        System.currentTimeMillis().toInt(),
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )
}

@RequiresApi(Build.VERSION_CODES.O)
private fun getNotificationChannel(
    context: Context,
    notificationManager: NotificationManager,
    channelId: String
) {
    val notificationChannel = NotificationChannel(
        channelId,
        context.getString(R.string.app_name),
        NotificationManager.IMPORTANCE_HIGH
    ).also {
        it.enableLights(true)
        it.enableVibration(true)
    }

    notificationManager.createNotificationChannel(notificationChannel)
}
