package com.practica.policeubgapp.ui.screens.homeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.ui.components.CarrouselComponent
import com.practica.policeubgapp.ui.components.ServiceDetailSheet
import com.practica.policeubgapp.ui.components.TargetPendingServiceComponent


///en este home vamos a mostrar los servicios que tiene que realizar el efectivo y publicidad
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController
){
     val hViewModel: HomeScreenViewModel = hiltViewModel()
    val publicityList by hViewModel.publicity.observeAsState(initial = emptyList())
    val serviceList by hViewModel.listOfServices.observeAsState(initial = emptyList())

    val sheetState = rememberModalBottomSheetState()
    var selectedService by remember { mutableStateOf<PendingServiceUI?>(null) }
    var showSheet by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        //carrousel de publicidad
        CarrouselComponent(
            list = publicityList
        )
        ///lista de servicios
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(serviceList) { servicePendingUi ->
                TargetPendingServiceComponent(servicePendingUi ){
                    selectedService = servicePendingUi
                    showSheet = true
                }
            }
        }
    }
    if (showSheet && selectedService != null) {
        ServiceDetailSheet(
            service = selectedService!!,
            sheetState = sheetState,
            onDismiss = {
                showSheet = false
                selectedService = null
            },
            onStartService = {
               // navController.navigate()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = NavHostController(LocalContext.current))
}