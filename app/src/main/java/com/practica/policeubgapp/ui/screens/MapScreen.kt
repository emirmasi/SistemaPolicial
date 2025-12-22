package com.practica.policeubgapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.practica.policeubgapp.ui.components.BottomBarComponent
import com.practica.policeubgapp.ui.components.KmProgressBar
import com.practica.policeubgapp.ui.components.TopAppBarComponent

//en este componente vamos a mostrar el mapa interactivo con las burbujas y las cant de km recorridos
//dependiendo del estado que se encuentre el efectivo
@Composable
fun MapScreen(navController: NavHostController){

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = "Mapa de servicios"
            ) {

            }
        },
        bottomBar = { BottomBarComponent(navController) }
    ) {
        innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            ) {
            KmProgressBar(7.5f)
        }
    }

}
@Preview(showBackground = true)
@Composable
fun MapScreenPreview(){
    MapScreen(navController = NavHostController(LocalContext.current))
}

