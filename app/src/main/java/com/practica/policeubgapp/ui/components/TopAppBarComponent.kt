package com.practica.policeubgapp.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent(
    title: String,
    navigationIcon: @Composable () -> Unit,
    actions: @Composable () -> Unit
){
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Medium
            )
        },
        navigationIcon = {
            navigationIcon()
        },
        actions = {
            actions()
        }
    )
}

@Preview
@Composable
fun TopAppBarPreview(){
    TopAppBarComponent(
        "Servicios realizados",
        { }
    ) {

    }
}