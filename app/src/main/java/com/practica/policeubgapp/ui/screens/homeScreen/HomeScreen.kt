package com.practica.policeubgapp.ui.screens.homeScreen

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.practica.policeubgapp.service.location.LocationService
import com.practica.policeubgapp.ui.components.CarrouselComponent
import com.practica.policeubgapp.ui.components.RequestNotificationPermission
import com.practica.policeubgapp.ui.components.ServiceDetailSheet
import com.practica.policeubgapp.ui.components.TargetPendingServiceComponent
import com.practica.policeubgapp.ui.navigations.NavigationRoutes


///en este home vamos a mostrar los servicios que tiene que realizar el efectivo y publicidad
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    hViewModel: HomeScreenViewModel
){
    val publicityList by hViewModel.publicity.observeAsState(initial = emptyList())
    val serviceList by hViewModel.listOfServices.collectAsState(initial = emptyList())
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState()

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
                    hViewModel.setSelectedPendingUI(servicePendingUi)
                    showSheet = true
                }
            }
        }
    }
    var selectedService = hViewModel.getSelectedPendingUI()
    if (showSheet && selectedService!= null) {
        RequestNotificationPermission()
        ServiceDetailSheet(
            service = selectedService,
            sheetState = sheetState,
            onDismiss = {
                showSheet = false
                selectedService = null
            },
            onStartService = {
                val intent = Intent(context, LocationService::class.java)
                context.startForegroundService(intent)
                hViewModel.starTime()
                showSheet = false
                navController.navigate(NavigationRoutes.Deployment.route)
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(
        navController = NavHostController(LocalContext.current),
        hViewModel = hiltViewModel()
    )
}