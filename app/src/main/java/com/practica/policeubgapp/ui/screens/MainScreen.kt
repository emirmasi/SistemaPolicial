package com.practica.policeubgapp.ui.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.practica.policeubgapp.domain.models.DISTRICT
import com.practica.policeubgapp.domain.models.PoliceDate
import com.practica.policeubgapp.domain.models.Rank
import com.practica.policeubgapp.ui.components.BottomBarComponent
import com.practica.policeubgapp.ui.components.DataPoliceComponent
import com.practica.policeubgapp.ui.components.TopAppBarComponent
import com.practica.policeubgapp.ui.navigations.NavigationComponent
import com.practica.policeubgapp.ui.navigations.NavigationRoutes
import kotlinx.coroutines.launch

// /en esta screen solo me faltaria traer los datos del policia para mostrarlos en la parte superior y en la parte de datos
@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen( controller: NavHostController) {

    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scope = rememberCoroutineScope ()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    LaunchedEffect(currentRoute) {
        println("The currentRoute is: $currentRoute")
    }
    ModalNavigationDrawer(
        drawerContent = {
            Column(
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState()),
            ) {
                Spacer(Modifier.height(12.dp))
                DataPoliceComponent(
                    datePolice =
                        PoliceDate(
                            7960,
                            "Masi",
                            "Isaias",
                            Rank.INSPECTOR,
                            department = "comisaria 3b",
                            DISTRICT.C12,
                            "https://i0.wp.com/www.palermomio.com.ar/wp-content/uploads/2017/01/PoliciaCiudadLogo.png?resize=250%2C187&ssl=1",
                        ),
                )
                NavigationDrawerItem(
                    label = { Text("Mis servicios") },
                    selected = currentRoute == NavigationRoutes.ServicesData.route,
                    onClick = {
                        controller.navigate(route = NavigationRoutes.ServicesData.route) {
                            popUpTo(controller.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                        scope.launch { drawerState.close() }
                    },
                )
                NavigationDrawerItem(
                    label = { Text("Salir") },
                    selected = currentRoute == NavigationRoutes.Login.route,
                    onClick = {
                        controller.navigate(route = NavigationRoutes.Login.route) {
                            popUpTo(0) { inclusive = true }
                        }
                        scope.launch { drawerState.close() }
                    },
                )
            }
        },
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.scrim,
    ) {
        Scaffold(
            topBar = {
                if(currentRoute != NavigationRoutes.Login.route){
                    TopAppBarComponent(
                        title = "Hola, inspector masi", // /aca va el estado de la
                        drawerState = drawerState,)
                }
            },
            bottomBar = {
                if (currentRoute != NavigationRoutes.Login.route)
                    BottomBarComponent(navController = controller)
            },
        ) { innerPadding ->
//            NavigationComponent(
//                navigationController = controller,
//                modifier = Modifier.padding(innerPadding),
//            )
            Box(modifier = Modifier.padding(innerPadding)){

            }
        }
    }
}

//@RequiresApi(Build.VERSION_CODES.P)
//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    MainScreen(
//    )
//}

// @Preview(showBackground = true)
// @Composable
// fun MainScreenPreview() {
//    MaterialTheme {
//        // En lugar de llamar a MainScreen(), dibuja el componente visual
//        // que quieres probar (ej. el Drawer o el Scaffold con un texto dummy)
//        Scaffold(
//            topBar = { /* Un TopBar estático */ }
//        ) { padding ->
//            Column(Modifier.padding(padding)) {
//                Text("Simulación de pantalla interna")
//            }
//        }
//    }
// }
