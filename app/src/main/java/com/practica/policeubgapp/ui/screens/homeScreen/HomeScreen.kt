package com.practica.policeubgapp.ui.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.ui.components.CarrouselComponent
import com.practica.policeubgapp.ui.components.TargetPendingServiceComponent


///en este home vamos a mostrar los servicios que tiene que realizar el efectivo y publicidad
@Composable
fun HomeScreen(
    navController: NavHostController
){
     val hViewModel: HomeScreenViewModel = hiltViewModel()
    val publicityList by hViewModel.publicity.observeAsState(initial = emptyList())
    val serviceList by hViewModel.listOfServices.observeAsState(initial = emptyList())
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CarrouselComponent(
            list = publicityList
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(serviceList) { servicePendingUi ->
                TargetPendingServiceComponent(servicePendingUi)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = NavHostController(LocalContext.current))
}