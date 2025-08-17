package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.practica.policeubgapp.R

@Composable
fun CircleImage(){
    Image(
        painter = painterResource(R.drawable.ciudad_logo1),
        contentDescription = "foto de perfil",
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape)
            .border(2.dp, color = Color(25, 76, 115, 255), CircleShape),
        contentScale = ContentScale.Crop
    )
}
