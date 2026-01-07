package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.MarkerData

@Composable
fun MapComponent(
    modifier: Modifier,
    cameraPositionState: CameraPositionState,
    cabaBounds: LatLngBounds,
    comisarias: List<Comisaria>,
    hospitales: List<MarkerData>,
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        GoogleMap(
            modifier = Modifier,
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                isMyLocationEnabled = true, // Muestra el punto azul del policía
                minZoomPreference = 11f
            ),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = true
            )
        ) {
            // Pintar Comisarías (Icono Azul)
            comisarias.forEach { comisaria ->
                comisaria.location?.let {
                    Marker(
                        state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                        title = comisaria.name,
                        snippet = "Dirección: ${comisaria.address}\nBarrio: ${comisaria.neighborhood}",
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)
                    )
                }
            }
            //pintar hospitales(icono rojo)
            hospitales.forEach { hospitales->
                hospitales.location?.let {
                    Marker(
                        state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                        title = hospitales.name,
                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
                    )
                }
            }
        }
    }

}