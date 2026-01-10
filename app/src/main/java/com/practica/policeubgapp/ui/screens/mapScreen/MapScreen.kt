package com.practica.policeubgapp.ui.screens.mapScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState
import com.practica.policeubgapp.ui.components.CardInformative
import com.practica.policeubgapp.ui.components.MapComponent

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(
    navController: NavHostController,
) {
    val mapScreenViewModel: MapScreenViewModel = hiltViewModel()
    val comisarias by mapScreenViewModel.comisarias.collectAsState()
    val infoComuna by mapScreenViewModel.comunaSeleccionada.collectAsState()
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mapScreenViewModel.cabaCenter, 12f)
    }
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    Scaffold {
        innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (locationPermissionsState.allPermissionsGranted) {
                // SI TIENE PERMISO: Mostramos el mapa
                MapComponent(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    cameraPositionState = cameraPositionState,
                    comisarias = comisarias,
                ) { comuna, barrios ->
                    mapScreenViewModel.seleccionarComuna(comuna, barrios)
                }
                AnimatedVisibility(
                    visible = infoComuna != null,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    CardInformative(
                        infoComuna = infoComuna, modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 350.dp)
                    )
                }
            } else {
                // NO TIENE PERMISO: Mostramos pantalla de solicitud
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val textToShow = if (locationPermissionsState.shouldShowRationale) {
                        // El usuario denegó una vez, explicamos por qué es vital para un policía
                        "La ubicación es necesaria para visualizar las comisarías cercanas y tu patrullaje."
                    } else {
                        // Es la primera vez o denegó permanentemente
                        "Para utilizar el mapa de CABA, necesitamos acceso a tu ubicación."
                    }

                    Text(text = textToShow, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { locationPermissionsState.launchMultiplePermissionRequest() }) {
                        Text("Conceder Permiso")
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MapScreenPreview(){
    MapScreen(navController = NavHostController(LocalContext.current))
}

