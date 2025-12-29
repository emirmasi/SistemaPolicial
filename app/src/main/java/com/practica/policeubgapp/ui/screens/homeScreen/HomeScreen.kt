package com.practica.policeubgapp.ui.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.ui.components.CarrouselComponent


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
        CarrouselComponent(
            list = listOf(
                Publicity(
                    id = "1",
                    image = "https://i.postimg.cc/wBRvJFfq/publicidad_Banco_Ciudad.jpg",
                    link = "https://www.google.com",
                    active = true
                ),
                Publicity(
                    id = "2",
                    image = "https://i.postimg.cc/wBRvJFfq/publicidad_Banco_Ciudad.jpg",
                    link = "https://www.google.com",
                    active = true
                ),
                Publicity(
                    id = "2",
                    image = "https://i.postimg.cc/wBRvJFfq/publicidad_Banco_Ciudad.jpg",
                    link = "https://www.google.com",
                    active = true
                )
            )
        )
        //todo mostar tarjeta de servicio a realizar es un lazyColumn ya que puede tener varios servicios
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = NavHostController(LocalContext.current))
}