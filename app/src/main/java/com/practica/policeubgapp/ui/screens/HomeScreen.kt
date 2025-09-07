package com.practica.policeubgapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController


///en este home vamos a mostrar los servicios que tiene que realizar el efectivo y publicidad
@Composable
fun HomeScreen(
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //todo: carrousel con publicidad
        //todo mostar tarjeta de servicio a realizar es un lazyColumn ya que puede tener varios servicios
    }
}