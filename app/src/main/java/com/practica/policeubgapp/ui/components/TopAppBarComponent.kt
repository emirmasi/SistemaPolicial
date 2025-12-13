package com.practica.policeubgapp.ui.components

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.painterResource
import com.practica.policeubgapp.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    title: String,
    drawerState: DrawerState
){
    val scope = rememberCoroutineScope()
    TopAppBar(
        title = {Text(text = title)},
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
                Icon(painter = painterResource(R.drawable.baseline_menu_24),
                    contentDescription = "Menu")
            }
        },
        actions = {

        }
    )
}