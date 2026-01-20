package com.practica.policeubgapp.ui.screens.mainScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.practica.policeubgapp.R
import com.practica.policeubgapp.ui.components.BottomBarComponent
import com.practica.policeubgapp.ui.components.TopAppBarComponent
import com.practica.policeubgapp.ui.navigations.InternalNavComponent
import com.practica.policeubgapp.ui.navigations.NavigationRoutes

@RequiresApi(Build.VERSION_CODES.P)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen( controller: NavHostController) {

    val internalController = rememberNavController()
    val navBackStackEntry by internalController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute != NavigationRoutes.Login.route && currentRoute != NavigationRoutes.Profile.route && currentRoute != NavigationRoutes.Deployment.route) {
                TopAppBarComponent(
                    title = "Policia de la Ciudad",
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                internalController.navigate(NavigationRoutes.Profile.route) {
                                    // Esto evita que se acumulen múltiples pantallas de perfil
                                    launchSingleTop = true
                                    // Si quieres que al volver siempre vayas al Home:
                                    popUpTo(NavigationRoutes.Home.route) { saveState = true }
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_person_24),
                                contentDescription = "perfil"
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                controller.navigate(NavigationRoutes.Login.route) {
                                    popUpTo(NavigationRoutes.Home.route) { inclusive = true }
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_logout_24),
                                contentDescription = "desloguearse"
                            )
                        }
                    }
                )
            }
        },
        bottomBar = {
            BottomBarComponent(navController = internalController)
        },
    ) { innerPadding ->
        InternalNavComponent(internalController = internalController)
    }

}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        controller = NavHostController(LocalContext.current),
    )
}

