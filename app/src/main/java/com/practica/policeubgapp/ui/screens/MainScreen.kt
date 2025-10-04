package com.practica.policeubgapp.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.practica.policeubgapp.domain.models.DISTRICT
import com.practica.policeubgapp.domain.models.PoliceDate
import com.practica.policeubgapp.domain.models.Rank
import com.practica.policeubgapp.ui.components.BottomBarComponent
import com.practica.policeubgapp.ui.components.DataPoliceComponent
import com.practica.policeubgapp.ui.components.TopAppBarComponent
import com.practica.policeubgapp.ui.navigations.NavigationComponent
import com.practica.policeubgapp.ui.navigations.NavigationRoutes

///en esta screen solo me faltaria traer los datos del policia para mostrarlos en la parte superior y en la parte de datos
@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val controller = rememberNavController()
    val navBackStackEntry = controller.currentBackStackEntry
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    //val scope = rememberCoroutineScope()

    if (currentRoute != NavigationRoutes.Login.route)
    {
        NavigationComponent(controller, modifier = Modifier.fillMaxSize())
    }else{
        ModalNavigationDrawer(
            drawerContent = {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                )  {
                    Spacer(Modifier.height(12.dp))
                    DataPoliceComponent(
                        datePolice = PoliceDate(7960,"Masi","Isaias", Rank.INSPECTOR, department = "comisaria 3b",
                            DISTRICT.C12,"https://i0.wp.com/www.palermomio.com.ar/wp-content/uploads/2017/01/PoliciaCiudadLogo.png?resize=250%2C187&ssl=1")
                    )
                    NavigationDrawerItem(
                        label = {Text("Mis servicios")},
                        selected = currentRoute == NavigationRoutes.ServicesData.route,
                        onClick = {
                            controller.navigate(route = NavigationRoutes.ServicesData.route){
                                popUpTo(controller.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                    NavigationDrawerItem(
                        label = {Text("Salir")},
                        selected = currentRoute == NavigationRoutes.Login.route,
                        onClick = {
                            controller.navigate(route = NavigationRoutes.Login.route){
                                popUpTo(controller.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )

                }
            },
            drawerState = drawerState,
            scrimColor = MaterialTheme.colorScheme.scrim,
        ) {
            Scaffold (
                topBar = {
                    if(currentRoute != NavigationRoutes.Login.route){
                        TopAppBarComponent(
                            title = "Hola, inspector masi",///aca va el estado de la
                            drawerState = drawerState
                        )
                    }

                },
                bottomBar = {
                    if(currentRoute != NavigationRoutes.Login.route){
                        BottomBarComponent(navController = controller)
                    }

                }
            ){ innerPadding ->
                ///aca va el HomeScreen
                NavigationComponent(
                    navigationController = controller,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }

}



@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}