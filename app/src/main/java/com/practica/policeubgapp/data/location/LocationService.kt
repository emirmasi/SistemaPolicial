package com.practica.policeubgapp.data.location

import android.Manifest
import android.app.Notification
import android.app.Service
import android.content.Intent
import android.content.pm.ServiceInfo
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.os.Looper
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.practica.policeubgapp.R
import kotlinx.coroutines.flow.MutableSharedFlow

class LocationService : Service() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    // Usamos una referencia estática o inyectada al ViewModel (o un SharedFlow)
    // Para simplificar, usaremos un canal de comunicación común
    companion object {
        val locationData = MutableSharedFlow<Location>(extraBufferCapacity = 1)
    }

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { location ->
                    locationData.tryEmit(location)
                }
            }
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
        fusedLocationClient.requestLocationUpdates(request, locationCallback, Looper.getMainLooper())
    }

    private fun createNotification(): Notification {
        // Debes crear un canal de notificación previo en Android 8+
        return NotificationCompat.Builder(this, "location_channel")
            .setContentTitle("Servicio Policial Activo")
            .setContentText("Rastreando ubicación y distancia...")
            .setSmallIcon(R.drawable.ciudad_logo1) // Tu icono
            .setOngoing(true)
            .build()
    }
    override fun onDestroy() {
        super.onDestroy()
        // Muy importante para liberar el GPS y ahorrar batería
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onBind(intent: Intent?): IBinder? = null
}