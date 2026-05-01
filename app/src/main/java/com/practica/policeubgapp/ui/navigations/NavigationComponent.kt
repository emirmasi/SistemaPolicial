package com.practica.policeubgapp.ui.navigations

import android.app.ActivityManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.practica.policeubgapp.ui.screens.deploymentScreen.DeploymentScreen
import com.practica.policeubgapp.ui.screens.homeScreen.HomeScreen
import com.practica.policeubgapp.ui.screens.homeScreen.HomeScreenViewModel
import com.practica.policeubgapp.ui.screens.loginScreen.LoginScreen
import com.practica.policeubgapp.ui.screens.mainScreen.MainScreen
import com.practica.policeubgapp.ui.screens.mapScreen.MapScreen
import com.practica.policeubgapp.ui.screens.serviceScreen.ServiceScreen
import com.practica.policeubgapp.ui.screens.profileScreen.ProfileScreen
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
    internalController: NavHostController,
    innerPadding: PaddingValues
){
    val sharedViewModel: HomeScreenViewModel = hiltViewModel()
    NavHost(
        navController = internalController,
        startDestination = NavigationRoutes.Home.route
    ){
        composable(route = NavigationRoutes.Home.route){
            HomeScreen(internalController, innerPadding,sharedViewModel)
        }
        composable(route = NavigationRoutes.ServicesData.route){
            ServiceScreen(internalController)
        }
        composable(route = NavigationRoutes.Map.route){
            MapScreen(internalController)
        }
        composable(route = NavigationRoutes.Profile.route){
            ProfileScreen(navController = internalController)
        }
        composable(
            route = NavigationRoutes.Deployment.route,
            deepLinks = listOf(navDeepLink{uriPattern = "policeubgapp://deployment"})
        ){
            DeploymentScreen(internalController, sharedViewModel)
        }

    }

}

fun isServiceRunning(context: Context, serviceClass: Class<*>): Boolean {
    val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    @Suppress("DEPRECATION") // Para versiones antiguas, pero sigue funcionando bien
    for (service in manager.getRunningServices(Int.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}

