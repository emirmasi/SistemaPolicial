package com.practica.policeubgapp.ui.navigations

sealed class NavigationRoutes(
    val route: String,
) {
    object Login : NavigationRoutes("loginScreen")
    object Home : NavigationRoutes("homeScreen")
    object DataService : NavigationRoutes("dataServiceScreen")
}