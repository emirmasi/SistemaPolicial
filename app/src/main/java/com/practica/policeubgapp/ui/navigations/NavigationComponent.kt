package com.practica.policeubgapp.ui.navigations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.practica.policeubgapp.ui.screens.HomeScreen
import com.practica.policeubgapp.ui.screens.LoginScreen
import com.practica.policeubgapp.ui.screens.MapScreen
import com.practica.policeubgapp.ui.screens.ServiceScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavigationComponent(
    navigationController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navigationController,
        startDestination = NavigationRoutes.Login.route,
        modifier = modifier
    ){
        composable(route = NavigationRoutes.Login.route){
            LoginScreen(navController = navigationController)
        }
        composable(route = NavigationRoutes.Home.route){
            HomeScreen(navigationController)
        }
        composable(route = NavigationRoutes.ServicesData.route){
            ServiceScreen(navigationController)
        }
        composable(route = NavigationRoutes.Map.route){
            MapScreen(navigationController)
        }
    }
}
