package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.practica.policeubgapp.R
import com.practica.policeubgapp.domain.models.CompletedServiceUI
import com.practica.policeubgapp.domain.models.capitalizeFirst

//aca deberia ir por estado un item de un servicio

@Composable
fun DataServiceComponent(
    service: CompletedServiceUI
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            // Columna izquierda (datos principales)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(R.drawable.baseline_hourglass_bottom_24),
                        contentDescription = "Horario",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp)
                    )
                    Text(
                        text = service.schedule.timeRange,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(R.drawable.baseline_place_24),
                        contentDescription = "QTH",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = service.locationName.capitalizeFirst(),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource( R.drawable.baseline_run_circle_24),
                        contentDescription = "Kilómetros",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${service.totalDistanceKm} KM",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            // Columna derecha (fecha y fiscalizado)
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = service.date,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = service.typeService.name,
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "${service.startTime} hs - ${service.endTime} hs",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "${service.totalHours} Horas",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DataServiceComponentPreview(){
//    DataServiceComponent(
//        service = CompletedService(
//            lp = 6252,,
//        date = , // Fecha del servicio typeService: TYPESERVICE,
//    schedule: SCHEDULE, // Tu enum de franja horaria (mañana/tarde/noche)
//     locationName: String,
//     geoPoint: GeoPoint?,
//        cantKm: Float,
//     startTime: Timestamp,     // Hora exacta que tocó "Iniciar"
//     endTime: Timestamp,       // Hora exacta que tocó "Terminar"
//     totalHours: Double,       // Calculado (ej: 8.5 horas)
//     totalDistanceKm: Float
//        )
//    )
//}