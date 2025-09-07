package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun KmProgressBar(
    kmRecorridos: Float, // kilómetros actuales
    modifier: Modifier = Modifier
) {
    val maxKm = 8f

    // Calculamos el progreso (0f a 1f)
    val progress = (kmRecorridos / maxKm).coerceIn(0f, 1f)

    // Definimos el color según los km
    val progressColor = when {
        kmRecorridos <= 4f -> Color.Red
        kmRecorridos <= 7f -> Color.Yellow
        else -> Color.Green
    }

    // Contenedor de la barra
    Box(
        modifier
            .fillMaxWidth()
            .height(24.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(12.dp))
    ) {
        // Barra progresiva
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(progress)
                .background(progressColor, shape = RoundedCornerShape(12.dp))
        )
        // Texto con km recorridos
        Text(
            text = "${kmRecorridos} km",
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun KmProgressBarPreview() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        KmProgressBar(kmRecorridos = 2f)
        KmProgressBar(kmRecorridos = 5f)
        KmProgressBar(kmRecorridos = 7.5f)
    }
}