package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.practica.policeubgapp.R

@Composable
fun BottomBarComponent(
    navController: NavHostController,
){
    BottomAppBar {
        Row(modifier = Modifier
            .fillMaxWidth()) {
            TarjetNavigate()
        }
    }
}
@Composable
fun TarjetNavigate() {
    Column (
        modifier = Modifier.padding(3.dp)
    ){
        Icon(
            painter = painterResource(id = R.drawable.baseline_visibility_24),
            contentDescription = null
        )

    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarComponentPreview(){
    BottomBarComponent(
        navController = NavHostController(LocalContext.current)
    )
}