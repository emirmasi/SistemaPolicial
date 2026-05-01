package com.practica.policeubgapp.service.location

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.ServiceInfo
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.practica.policeubgapp.MainActivity
import com.practica.policeubgapp.R
import kotlinx.coroutines.flow.MutableSharedFlow

class LocationService : Service() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private var lastLocationLocal: Location? = null
    companion object {
        val locationData = MutableSharedFlow<Location>(extraBufferCapacity = 1)
        var distanciaAcumuladaMetros = 0f
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { location ->
                    lastLocationLocal?.let { prev->
                        val distance = location.distanceTo(prev)
                        if(distance > 2.0){
                            distanciaAcumuladaMetros += distance
                            Log.d("LocationService", "Distancia recorrida: $distanciaAcumuladaMetros")
                            val km  = distanciaAcumuladaMetros/1000f
                            updateNotification(km)

                        }
                    }
                    lastLocationLocal = location
                    locationData.tryEmit(location)
                }
            }
        }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "location_channel",
                "Seguimiento de Patrullaje",
                NotificationManager.IMPORTANCE_LOW // LOW para que no moleste con sonidos cada 5 seg
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            startForeground(1, createNotification(), ServiceInfo.FOREGROUND_SERVICE_TYPE_LOCATION)
        }else{
            startForeground(1, createNotification())
        }
        // Notificación obligatoria
        startLocationUpdates()
        return START_STICKY
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    private fun startLocationUpdates() {
        val request = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 5000) // Cada 5 seg
            .setMinUpdateDistanceMeters(2f) // Cada 2 metros
            .build()
        try {
            fusedLocationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
        }catch (e:Exception){
            Log.e("LocationService", "Error al iniciar la ubicación", e)
        }

    }
    private fun createNotification(distancia: Float = 0f): Notification {
        val channelId = "location_channel"

        // 1. Crear el Intent que abre la MainActivity
        val intent = Intent(Intent.ACTION_VIEW, "policeubgapp://deployment".toUri(),this, MainActivity::class.java)
        // 2. Crear el PendingIntent (el "ticket" para que Android abra la app por nosotros)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Servicio Policial Activo")
            .setContentText("Distancia recorrida: ${String.format("%.2f", distancia)} KM")
            .setSmallIcon(R.drawable.escudo_ciudad2)
            .setOngoing(true)
            .setOnlyAlertOnce(true) // Importante: evita que el celular suene/vibre en cada actualización
            .setContentIntent(pendingIntent) // <--- Aquí vinculamos el toque
            .build()
    }

    private fun updateNotification(distancia: Float) {
        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(
            1,
            createNotification(distancia)
        ) // El ID '1' debe ser el mismo que en startForeground
    }
    override fun onDestroy() {
        super.onDestroy()
        // Muy importante para liberar el GPS y ahorrar batería
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}