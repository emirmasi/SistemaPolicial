package com.practica.policeubgapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.practica.policeubgapp.domain.models.CompletedService
import com.practica.policeubgapp.domain.models.DISTRICT
import com.practica.policeubgapp.domain.models.PoliceDate
import com.practica.policeubgapp.domain.models.Rank
import com.practica.policeubgapp.domain.models.SCHEDULE
import com.practica.policeubgapp.domain.models.TYPESERVICE
import com.practica.policeubgapp.ui.components.BottomBarComponent
import com.practica.policeubgapp.ui.components.DataServiceComponent
import com.practica.policeubgapp.ui.components.TopAppBarComponent

//esta screen va a mostrar todos los servicios que hizo el efectivo en el mes
@Composable
fun ServiceScreen(
    controller: NavHostController
){
    //puedo poner un filtro , que filtre x dia , x mes ,x horario etc
    //todo: en el viewModel debo crear una lista de servicios ordenados por fechas ambas formas
    val services = listOf(
        CompletedService(
            lp = 1234,
            typeService = TYPESERVICE.UBG,
            date = "28/9/2025",
            schedule = SCHEDULE.MAÑANA,
            location = "av la plata",
            cantKm = 7.5f,
            supervised = PoliceDate(
                lp = 1234,
                lastName = "masi",
                firstName = "Juan Perez",
                rank = Rank.INSPECTOR,
                department = "policia",
                district = DISTRICT.C12,
                photoUrl = "foto"
            )
        ),
        CompletedService(
            lp = 1234,
            typeService = TYPESERVICE.CONSIGNA,
            date = "28/9/2025",
            schedule = SCHEDULE.MAÑANA,
            location = "av la plata",
            cantKm = 7.5f,
            supervised = PoliceDate(
                lp = 1234,
                lastName = "masi",
                firstName = "Juan Perez",
                rank = Rank.INSPECTOR,
                department = "policia",
                district = DISTRICT.C12,
                photoUrl = "foto"
            )
        ),
        CompletedService(
            lp = 1234,
            typeService = TYPESERVICE.SATURACION,
            date = "28/9/2025",
            schedule = SCHEDULE.MAÑANA,
            location = "av la plata",
            cantKm = 7.5f,
            supervised = PoliceDate(
                lp = 1234,
                lastName = "masi",
                firstName = "Juan Perez",
                rank = Rank.INSPECTOR,
                department = "policia",
                district = DISTRICT.C12,
                photoUrl = "foto"
            )
        )
    )
    Scaffold(
        topBar = {
            TopAppBarComponent(
                "Servicios Realizados",
                navigationIcon = {

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