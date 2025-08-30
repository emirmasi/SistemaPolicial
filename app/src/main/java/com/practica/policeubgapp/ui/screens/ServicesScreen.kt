package com.practica.policeubgapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.practica.policeubgapp.ui.components.DataServiceComponent

//esta screen va a mostrar todos los servicios que hizo el efectivo en el mes
@Composable
fun ServiceScreen(
    controller: NavHostController
){
     val item = listOf<Int>(1,2,3,4,5,6,7)
    ///debo crear un componente que muestre todos los dato
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        item( item){
            DataServiceComponent()
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ServiceScreenPreview(){
    ServiceScreen(controller = NavHostController(LocalContext.current))
}