package com.practica.policeubgapp.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.google.maps.android.data.geojson.GeoJsonMultiPolygon
import com.google.maps.android.data.geojson.GeoJsonPolygon
import com.google.maps.android.data.geojson.GeoJsonPolygonStyle
import com.practica.policeubgapp.R
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.DatosPanel
import androidx.core.graphics.scale

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapComponent(
    modifier: Modifier,
    cameraPositionState: CameraPositionState,
    cabaBounds: LatLngBounds,
    comisarias: List<Comisaria>,
    hospitales: List<DatosPanel>,
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
                    var polygonStyle = layer.defaultPolygonStyle
                    polygonStyle.strokeColor = android.graphics.Color.BLUE // Color de la línea
                    polygonStyle.strokeWidth = 4f // Grosor
                    //polygonStyle.fillColor =
                        //android.graphics.Color.argb(30, 0, 0, 255) // Relleno azul muy transparente
                    layer.setOnFeatureClickListener { feature ->

                        // Aplicamos estilo de "Resaltado" a la comuna tocada
                        val highlightStyle = GeoJsonPolygonStyle().apply {
                            strokeColor = android.graphics.Color.RED
                            strokeWidth = 8f
                            fillColor = android.graphics.Color.argb(60, 255, 0, 0)
                        }
                        feature.apply { polygonStyle = highlightStyle }

                        // Esto busca el centro geométrico de la comuna y mueve la cámara
                        if (feature.geometry is GeoJsonPolygon || feature.geometry is GeoJsonMultiPolygon) {
                            // Nota: Para centrar polígonos complejos se suele usar LatLngBounds
                            // Pero por ahora, un simple mensaje de confirmación es suficiente para no marear al usuario.
                        }
                        //obtenes el numero de comuna y obtnemos los barrios que lo componen
                        val comuna = feature.getProperty("comuna")
                        val barrios = feature.getProperty("barrios")

                    }
                    layer.addLayerToMap()
                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }
            }
            val policeIcon = remember {
                val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.ciudad_logo1)
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
                        icon = policeIcon
                    )
                }
            }
            //pintar hospitales(icono rojo)
//            hospitales.forEach { hospitales->
//                hospitales.location?.let {
//                    Marker(
//                        state = MarkerState(position = LatLng(it.latitude, it.longitude)),
//                        title = hospitales.name,
//                        icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)
//                    )
//                }
//            }
        }
    }

}

fun bitmapDescriptorFromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}