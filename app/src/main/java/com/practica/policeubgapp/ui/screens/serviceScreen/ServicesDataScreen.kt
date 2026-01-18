package com.practica.policeubgapp.ui.screens.serviceScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.practica.policeubgapp.ui.components.BottomBarComponent
import com.practica.policeubgapp.ui.components.DataServiceComponent
import com.practica.policeubgapp.ui.components.TopAppBarComponent

//esta screen va a mostrar todos los servicios que hizo el efectivo en el mes
@Composable
fun ServiceScreen(
    controller: NavHostController
){
    val serviceVM :ServiceDataScreenViewModel = hiltViewModel()
    val services by serviceVM.listOfServiceComplete.collectAsState()

    Scaffold(
        topBar = {
            TopAppBarComponent(
                "Servicios Realizados",
                navigationIcon = {
                },
                actions = {
                }
            )
        },
        bottomBar = { BottomBarComponent(navController = controller) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)

        ) {
            items(services) { servicio ->
                DataServiceComponent(
                    service = servicio
                )
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun ServiceScreenPreview(){
    ServiceScreen(controller = NavHostController(LocalContext.current))
}