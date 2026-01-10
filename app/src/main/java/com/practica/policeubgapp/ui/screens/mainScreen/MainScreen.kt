package com.practica.policeubgapp.ui.screens.mainScreen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.practica.policeubgapp.R
import com.practica.policeubgapp.domain.models.DISTRICT
import com.practica.policeubgapp.domain.models.PoliceDate
import com.practica.policeubgapp.domain.models.Rank
import com.practica.policeubgapp.ui.components.BottomBarComponent
import com.practica.policeubgapp.ui.components.DataPoliceComponent
import com.practica.policeubgapp.ui.components.TopAppBarComponent
import com.practica.policeubgapp.ui.navigations.InternalNavComponent
import com.practica.policeubgapp.ui.navigations.NavigationRoutes
import kotlinx.coroutines.launch

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
            if (currentRoute != NavigationRoutes.Login.route && currentRoute != NavigationRoutes.Profile.route) {
                TopAppBarComponent(
                    title = "",
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

