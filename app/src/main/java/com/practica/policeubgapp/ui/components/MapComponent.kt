package com.practica.policeubgapp.ui.components

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.scale
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.practica.policeubgapp.R
import com.practica.policeubgapp.domain.models.Comisaria

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapComponent(
    modifier: Modifier,
    cameraPositionState: CameraPositionState,
    comisarias: List<Comisaria>,
    onComunaSelected: (Int, List<String>) -> Unit
) {
    val context = LocalContext.current

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
            ),
            onMapLoaded = {

            }
        ) {
            // Para cargar el GeoJSON de límites:
            MapEffect(Unit) { map ->
                try {
                    val layer = GeoJsonLayer(map, R.raw.comunas, context)

                    // Estilo de las líneas (azul como en tu imagen)
                    val polygonStyle = layer.defaultPolygonStyle
                    polygonStyle.strokeColor = android.graphics.Color.BLUE // Color de la línea
                    polygonStyle.strokeWidth = 6f // Grosor

                    layer.setOnFeatureClickListener { feature ->
                        //obtenes el numero de comuna y obtnemos los barrios que lo componen
                        val comunaId = feature.getProperty("comuna")?.toIntOrNull() ?: 0
                        val barrios = feature.getProperty("barrios")
                        onComunaSelected(comunaId, barrios.split(", "))
                    }
                    layer.addLayerToMap()
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
            val policeIcon = remember {
                val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.escudo_ciudad2)
                // Redimensionar a 100x100 píxeles (ajusta según necesites)
                val smallMarker = bitmap.scale(100, 100, false)
                BitmapDescriptorFactory.fromBitmap(smallMarker)
            }
            // Pintar Comisarías (Icono Azul)
            comisarias.forEach { comisaria ->
                comisaria.location?.let {
                    Marker(
                        state = MarkerState(position = LatLng(it.latitude, it.longitude)),
                        title = comisaria.name,
                        snippet = "Dirección: ${comisaria.address}\nBarrio: ${comisaria.neighborhood}",
                        icon = policeIcon,
                    )
                }
            }
        }
    }
}
