package com.practica.policeubgapp.ui.components


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.practica.policeubgapp.ui.navigations.NavigationRoutes

@Composable
fun BottomBarComponent(
    navController: NavHostController,
){

    Surface(
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 16.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp,
        shadowElevation = 8.dp
    ) {
        NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {

            val navBackStackEntry = navController.currentBackStackEntry
            val currentRoute = navBackStackEntry?.destination?.route

            val listRoutes = listOf(
                NavigationRoutes.Home,
                NavigationRoutes.ServicesData,
                NavigationRoutes.Map
            )
            listRoutes.forEach { screen ->
                NavigationBarItem(
                    selected = currentRoute == screen.route,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = screen.title)
                    }
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun BottomBarComponentPreview(){
    BottomBarComponent(
        navController = NavHostController(LocalContext.current)
    )
}