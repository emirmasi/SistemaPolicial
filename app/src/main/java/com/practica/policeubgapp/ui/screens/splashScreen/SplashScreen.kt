package com.practica.policeubgapp.ui.screens.splashScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.practica.policeubgapp.ui.navigations.NavigationRoutes
import com.practica.policeubgapp.ui.screens.splashScreen.SplashViewModel

@Composable
fun SplashScreen(navHostController: NavHostController) {
    val splashVm: SplashViewModel = hiltViewModel()

    val isUserAuth = splashVm.isUserAuthenticated.collectAsState()
    LaunchedEffect(isUserAuth.value) {
        if (isUserAuth.value == null) return@LaunchedEffect

        if (isUserAuth.value == true) {
            navHostController.navigate(route = NavigationRoutes.MainScreen.route) {
                popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
            }
        } else {
            navHostController.navigate(route = NavigationRoutes.Login.route) {
                popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
            }
        }
    }
}