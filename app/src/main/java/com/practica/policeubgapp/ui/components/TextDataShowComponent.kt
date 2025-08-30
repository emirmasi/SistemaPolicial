package com.practica.policeubgapp.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun <T> TextDataShowComponent(text: String,data: T){
    Text(
        text = "$text : $data",
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontStyle = FontStyle.Italic,
        maxLines = 1,

        )
}

@Preview
@Composable
fun TextDataShowComponentPreview(){
    TextDataShowComponent(text = "Comuna", data = "La Paz")
}