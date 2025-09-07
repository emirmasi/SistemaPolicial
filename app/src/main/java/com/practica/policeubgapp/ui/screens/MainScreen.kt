package com.practica.policeubgapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.practica.policeubgapp.ui.components.DataPoliceComponent
import com.practica.policeubgapp.ui.components.TopAppBarComponent
import com.practica.policeubgapp.ui.navigations.NavigationRoutes
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val controller = rememberNavController()
    val navBackStackEntry = controller.currentBackStackEntry
    val currentRoute = navBackStackEntry?.destination?.route

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            )  {
                Spacer(Modifier.height(12.dp))
                //aca debe ir una foto de perfil
                DataPoliceComponent()
                NavigationDrawerItem(
                    label = {Text("Mis servicios")},
                    selected = currentRoute == NavigationRoutes.ServicesData.route,
                    onClick = {
                        controller.navigate(route = NavigationRoutes.ServicesData.route)
                    }
                )
                NavigationDrawerItem(
                    label = {Text("Salir")},
                    selected = currentRoute == NavigationRoutes.Login.route,
                    onClick = {
                        controller.navigate(route = NavigationRoutes.Login.route)
                    }
                )

            }
        },
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.24f),
    ) {
        Scaffold (
            topBar = {
                TopAppBarComponent(
                    title = "Hola, inspector masi",///aca va el estado de la
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if(drawerState.isClosed){
                                        drawerState.open()
                                    } else {
                                        drawerState.close()
                                    }
                                }
                            }
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ){ innerPadding ->
            ///aca va el HomeScreen
        }
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen()
}