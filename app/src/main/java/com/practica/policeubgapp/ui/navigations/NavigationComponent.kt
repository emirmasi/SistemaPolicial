package com.practica.policeubgapp.ui.navigations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.practica.policeubgapp.ui.screens.homeScreen.HomeScreen
import com.practica.policeubgapp.ui.screens.loginScreen.LoginScreen
import com.practica.policeubgapp.ui.screens.mainScreen.MainScreen
import com.practica.policeubgapp.ui.screens.mapScreen.MapScreen
import com.practica.policeubgapp.ui.screens.ServiceScreen
import com.practica.policeubgapp.ui.screens.splashScreen.SplashScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ExternalNavComponent(
    navigationController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navigationController,
        startDestination = NavigationRoutes.Splash.route,
        modifier = modifier
    ){
        composable(route = NavigationRoutes.Splash.route){
            SplashScreen(navHostController = navigationController)
        }
        composable(route = NavigationRoutes.Login.route){
            LoginScreen(navController = navigationController)
        }
        composable(route = NavigationRoutes.MainScreen.route){
            MainScreen(navigationController)
        }

    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun InternalNavComponent(
    internalController: NavHostController
){
    NavHost(
        navController = internalController,
        startDestination = NavigationRoutes.Home.route
    ){
        composable(route = NavigationRoutes.Home.route){
            HomeScreen(internalController)
        }
        composable(route = NavigationRoutes.ServicesData.route){
            ServiceScreen(internalController)
        }
        composable(route = NavigationRoutes.Map.route){
            MapScreen(internalController)
        }
    }

}