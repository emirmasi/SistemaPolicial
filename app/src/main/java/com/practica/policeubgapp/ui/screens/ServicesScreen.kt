package com.practica.policeubgapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.practica.policeubgapp.ui.components.DataServiceComponent

//esta screen va a mostrar todos los servicios que hizo el efectivo en el mes
@Composable
fun ServiceScreen(
    controller: NavHostController
){
    ///debo crear un componente que muestre todos los dato
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
        DataServiceComponent()
    }
}

@Preview(showBackground = true)
@Composable
fun ServiceScreenPreview(){
    ServiceScreen(controller = NavHostController(LocalContext.current))
}