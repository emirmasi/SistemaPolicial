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

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun NavigationComponent(
    navigationController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navigationController,
        startDestination = NavigationRoutes.Login,
        modifier = modifier
    ){
        composable(route = NavigationRoutes.Login.route){
            LoginScreen(navController = navigationController)
        }
        composable(route = NavigationRoutes.Home.route){
            HomeScreen(navigationController)
        }

    }
}
