package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.practica.policeubgapp.domain.models.DatosPanel

@Composable
fun CardInformative(
    infoComuna: DatosPanel?,
    modifier: Modifier = Modifier
) {
    // Si no hay comuna seleccionada, no mostramos nada
    if (infoComuna == null) return

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()) // Por si hay muchos hospitales
        ) {
            // TÍTULO COMUNA
            Text(
                text = infoComuna.comuna,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A73E8) // Azul policial
            )

            // BARRIOS
            Text(
                text = infoComuna.barrios.joinToString(", "),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            HorizontalDivider(thickness = 1.dp, color = Color.LightGray)

            // SECCIÓN COMISARÍAS
            SectionHeader(title = "🚔 Comisarías", count = infoComuna.comisarias.size)
            infoComuna.comisarias.forEach { comisaria ->
                InfoItem(name = comisaria.name, detail = comisaria.address)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // SECCIÓN HOSPITALES
            SectionHeader(title = "🏥 Hospitales", count = infoComuna.hospitales.size)
            infoComuna.hospitales.forEach { hospital ->
                InfoItem(
                    name = hospital.name,
                    detail = "${hospital.address} (${hospital.specialty})"
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, count: Int) {
    Row(
        modifier = Modifier.padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Badge(
            modifier = Modifier.padding(start = 8.dp),
            containerColor = Color(0xFFE8F0FE),
            contentColor = Color(0xFF1A73E8)
        ) {
            Text(count.toString())
        }
    }
}

@Composable
fun InfoItem(name: String, detail: String) {
    Column(modifier = Modifier.padding(bottom = 8.dp, start = 4.dp)) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = detail,
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray
        )
    }
}