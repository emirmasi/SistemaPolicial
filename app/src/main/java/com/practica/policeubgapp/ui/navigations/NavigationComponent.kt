package com.practica.policeubgapp.ui.navigations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practica.policeubgapp.ui.screens.HomeScreen
import com.practica.policeubgapp.ui.screens.loginScreen.LoginScreen
import com.practica.policeubgapp.ui.screens.MainScreen
import com.practica.policeubgapp.ui.screens.MapScreen
import com.practica.policeubgapp.ui.screens.ServiceScreen
import com.practica.policeubgapp.ui.screens.SplashScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavigationComponent(
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
        composable(route = NavigationRoutes.Home.route){
            HomeScreen(navigationController)
        }
        composable(route = NavigationRoutes.ServicesData.route){
            ServiceScreen(navigationController)
        }
        composable(route = NavigationRoutes.MainScreen.route){
            MainScreen(navigationController)
        }
        composable(route = NavigationRoutes.Map.route){
            MapScreen(navigationController)
        }
    }
}
