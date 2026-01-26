package com.practica.policeubgapp

import android.app.Application
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import dagger.hilt.android.HiltAndroidApp
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory

@HiltAndroidApp
class PoliceApplication : Application(){

    override fun onCreate(){
        super.onCreate()
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        firebaseAppCheck.installAppCheckProviderFactory(
            DebugAppCheckProviderFactory.getInstance()
        )
        createNotificationChannel(this)
    }

}

fun createNotificationChannel(context: Context) {
    // Solo es necesario a partir de Android Oreo (API 26)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "location_channel" // Debe coincidir con el ID usado en tu Service
        val name = "Seguimiento de Servicio Policial"
        val descriptionText = "Notificación activa mientras el oficial está en servicio"
        val importance = NotificationManager.IMPORTANCE_LOW // Low para que no suene cada vez que aparece

        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        // Registrar el canal con el sistema
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
