package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.GeoPoint
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MiniMapaEstatico(ubicacion: GeoPoint?) {
    // Si la ubicación es nula, mostramos un placeholder
    if (ubicacion == null) {
        Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
            Text("Ubicación no disponible", Modifier.align(Alignment.Center))
        }
        return
    }

    val posicion = LatLng(ubicacion.latitude, ubicacion.longitude)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(posicion, 15f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        // PROPIEDADES CLAVE PARA QUE SEA ESTÁTICO
        properties = MapProperties(
            mapType = MapType.NORMAL,
            isMyLocationEnabled = false
        ),
        uiSettings = MapUiSettings(
            zoomControlsEnabled = false,
            scrollGesturesEnabled = false, // Bloquea el movimiento
            zoomGesturesEnabled = false,   // Bloquea el zoom
            tiltGesturesEnabled = false,
            mapToolbarEnabled = false      // Quita los botones de "Abrir en Maps"
        )
    ) {
        // Marcador en el lugar del servicio
        Marker(
            state = MarkerState(position = posicion),
            title = "Lugar del Servicio"
        )
    }
}