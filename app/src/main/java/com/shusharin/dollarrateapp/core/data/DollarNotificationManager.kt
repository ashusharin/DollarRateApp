package com.shusharin.dollarrateapp.core.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.shusharin.dollarrateapp.MainActivity
import com.shusharin.dollarrateapp.R
import com.shusharin.dollarrateapp.ui.ResourceProvider
import javax.inject.Inject

interface DollarNotificationManager {
    fun createChannel()
    fun showNotification()

    class Base @Inject constructor(
        private val context: Context,
        private val resourceProvider: ResourceProvider,
    ) :
        DollarNotificationManager {
        override fun createChannel() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = resourceProvider.getString(R.string.channel_name)
                val descriptionText = resourceProvider.getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                    description = descriptionText
                }
                val dollarNotificationManager =
                    getSystemService(context,
                        NotificationManager::class.java) as NotificationManager
                dollarNotificationManager.createNotificationChannel(channel)
            }
        }

        override fun showNotification() {
            createChannel()
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            val builder =
                NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(resourceProvider.getString(R.string.notification_title))
                    .setContentText(resourceProvider.getString(R.string.notification_text))
                    .setPriority(NotificationCompat.PRIORITY_MAX)
            builder.setContentIntent(pendingIntent).setAutoCancel(true)
            val notificationManager =
                getSystemService(context,
                    NotificationManager::class.java) as NotificationManager
            with(notificationManager) {
                notify(1, builder.build())
            }
        }

        companion object {
            private const val CHANNEL_ID = "dollar"
        }
    }
}