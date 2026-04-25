package com.practica.policeubgapp.ui.screens.deploymentScreen

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.practica.policeubgapp.service.location.LocationService
import com.practica.policeubgapp.ui.navigations.NavigationRoutes
import com.practica.policeubgapp.ui.screens.homeScreen.HomeScreenViewModel

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
    var showFinishDialog by remember { mutableStateOf(false) }
    val selectedService = sharedViewModel.getSelectedPendingUI()

    LaunchedEffect(sharedViewModel.currentPosition) {
        sharedViewModel.currentPosition?.let { pos ->
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLngZoom(pos, 17f),
                durationMs = 800 // Animación suave de casi 1 segundo
            )
        }
    }

    if(showFinishDialog){
        AlertDialog(
            onDismissRequest = {
                showFinishDialog = false
            },
            title = {
                Row {
                    Icon(
                        painter = painterResource(R.drawable.baseline_warning_24),
                        contentDescription = "Finalizar Servicio"
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Text(text = "Finalizar Servicio")
            },
            text = {
                Text(text = "¿Esta seguro de querer terminar el servico?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showFinishDialog = false
                        // Ejecutamos toda la lógica de cierre
                        sharedViewModel.endTime()
                        sharedViewModel.uploadCompletedService()

                        // Borramos el pendiente usando el UID que guardamos
                        selectedService?.let { sharedViewModel.deleteService(it.uid) }

                        // Detenemos el GPS y volvemos
                        context.stopService(Intent(context, LocationService::class.java))
                        internalController.navigate(NavigationRoutes.Home.route) {
                            popUpTo(NavigationRoutes.Home.route) { inclusive = true }
                        }
                    },
                    colors = ButtonColors(
                        containerColor = Color.Blue,
                        contentColor = MaterialTheme.colorScheme.onError,
                        disabledContainerColor = MaterialTheme.colorScheme.errorContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Text("Finalizar servicio")
                }
            },
            dismissButton = {
                TextButton(onClick = { showFinishDialog = false },
                    colors = ButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = MaterialTheme.colorScheme.onError,
                        disabledContainerColor = MaterialTheme.colorScheme.errorContainer,
                        disabledContentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                    ) {
                    Text("Cancelar")
                }
            }

        )
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Despliegue") },
                modifier = Modifier.fillMaxWidth(),
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
                    showFinishDialog = true
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Finalizar Servicio")
            }

        }
    }
}