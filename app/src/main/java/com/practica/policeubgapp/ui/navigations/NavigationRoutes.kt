package com.practica.policeubgapp.ui.navigations

import com.practica.policeubgapp.R

sealed class NavigationRoutes(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Splash : NavigationRoutes("splashScreen","splash",R.drawable.baseline_home_24)
    object Login : NavigationRoutes("loginScreen","login",R.drawable.baseline_login_24)
    object Home : NavigationRoutes("homeScreen","home",R.drawable.baseline_home_24)
    object ServicesData : NavigationRoutes("dataServiceScreen","servicios",R.drawable.baseline_format_list_numbered_24)
    object MainScreen : NavigationRoutes("mainScreen","main",R.drawable.baseline_home_24)
    object Map : NavigationRoutes("mapScreen","mapa",R.drawable.baseline_map_24)

    object Profile : NavigationRoutes("profileScreen","perfil",R.drawable.baseline_person_24)


}