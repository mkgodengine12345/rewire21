package com.rewire21.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RewireApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }

    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channels = listOf(
                NotificationChannel(
                    "rewire_service",
                    "REWIRE21 Service",
                    NotificationManager.IMPORTANCE_LOW
                ),
                NotificationChannel(
                    "rewire_lock",
                    "REWIRE21 Lock",
                    NotificationManager.IMPORTANCE_HIGH
                ),
                NotificationChannel(
                    "rewire_ai",
                    "REWIRE21 AI",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
            val manager = getSystemService(NotificationManager::class.java)
            channels.forEach { manager.createNotificationChannel(it) }
        }
    }
}
