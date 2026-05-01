package com.practica.policeubgapp.ui.screens.splashScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.practica.policeubgapp.R
import com.practica.policeubgapp.service.location.LocationService
import com.practica.policeubgapp.ui.navigations.NavigationRoutes
import com.practica.policeubgapp.ui.navigations.isServiceRunning

@Composable
fun SplashScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val splashVm: SplashViewModel = hiltViewModel()

    val isUserAuth by splashVm.isUserAuthenticated.collectAsState()

    LaunchedEffect(isUserAuth) {
        Log.d("SPLASH", "Estado de Auth: $isUserAuth")
        if (isUserAuth == null) return@LaunchedEffect

        if (isUserAuth == true) {
            Log.d("SPLASH", "Navegando a Main")
            if (isServiceRunning(context, LocationService::class.java)) {
                Log.d("SPLASH", "el servicio de location esta activado")
                navHostController.navigate(route = NavigationRoutes.MainScreen.route) {
                    popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
                }
            }else{
                Log.d("SPLASH", "el servicio de location no esta activado")
                navHostController.navigate(route = NavigationRoutes.MainScreen.route) {
                    popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
                }
            }
        } else {
            Log.d("SPLASH", "no hay nadie autenticado user es null")
            navHostController.navigate(route = NavigationRoutes.Login.route){
                popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
            }
        }

    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.escudo_ciudad2), contentDescription = null)
    }
}