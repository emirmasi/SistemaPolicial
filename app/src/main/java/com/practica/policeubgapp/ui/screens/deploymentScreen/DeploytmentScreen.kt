package com.practica.policeubgapp.ui.screens.deploymentScreen

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.scale
import androidx.navigation.NavHostController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.practica.policeubgapp.R
import com.practica.policeubgapp.data.location.LocationService
import com.practica.policeubgapp.ui.screens.homeScreen.HomeScreenViewModel

///en esta screen vamos a usar el despliegue de un servicio , tiene que mostrar el mapa con
//la ubicacion del usuario y contar la cant km recorridos y cambio el estado del efectivo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeploymentScreen(
    internalController: NavHostController,
    sharedViewModel: HomeScreenViewModel
) {
    val cameraPositionState = rememberCameraPositionState{
        val initialPos = sharedViewModel.currentPosition ?: LatLng(-34.6, -58.4)
        position = CameraPosition.fromLatLngZoom(initialPos, 17f)
    }
    val context = LocalContext.current

    LaunchedEffect(sharedViewModel.currentPosition) {
        sharedViewModel.currentPosition?.let { pos ->
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(pos, 17f),
                durationMs = 800 // Animación suave de casi 1 segundo
            )
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Despliegue") },
                modifier = Modifier.fillMaxWidth(),
                actions = {
                    IconButton(
                        onClick = {
                            internalController.navigate("homeScreen"){
                                popUpTo("homeScreen"){
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                            contentDescription = "Actualizar",
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
            ) {
                GoogleMap(
                    modifier = Modifier.padding(4.dp),
                    cameraPositionState = cameraPositionState,
                    properties = MapProperties(isMyLocationEnabled = true), // Muestra el punto azul nativo
                    uiSettings = MapUiSettings(myLocationButtonEnabled = true)
                ) {
                    val policeIcon = remember {
                        val bitmap = BitmapFactory.decodeResource(
                            internalController.context.resources,
                            R.drawable.ciudad_logo1
                        )
                        // Redimensionar a 100x100 píxeles (ajusta según necesites)
                        val smallMarker = bitmap.scale(100, 100, false)
                        BitmapDescriptorFactory.fromBitmap(smallMarker)
                    }
                    sharedViewModel.currentPosition?.let {
                        Marker(
                            state = MarkerState(position = it),
                            title = "tu ubicacion",
                            icon = policeIcon
                        )
                    }

                }
            }
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = "Distancia: ${sharedViewModel.getDistanceKm()} KM",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.headlineSmall            )
            }
            Button(
                onClick = {
                    ///aca debo ir a la pantalla de home y debo alzar el servicio a la db
                    // Detener el servicio para ahorrar batería
                    context.stopService(Intent(context, LocationService::class.java))
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Finalizar Servicio")
            }

        }
    }
}