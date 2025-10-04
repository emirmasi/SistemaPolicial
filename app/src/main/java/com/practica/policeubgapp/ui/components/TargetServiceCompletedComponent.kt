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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica.policeubgapp.R
import com.practica.policeubgapp.domain.models.DISTRICT
import com.practica.policeubgapp.domain.models.PoliceDate
import com.practica.policeubgapp.domain.models.SCHEDULE
import com.practica.policeubgapp.domain.models.Rank
import com.practica.policeubgapp.domain.models.CompletedService
import com.practica.policeubgapp.domain.models.TYPESERVICE
import com.practica.policeubgapp.domain.models.capitalizeFirst

//aca deberia ir por estado un item de un servicio

@Composable
fun DataServiceComponent(
    servicio: CompletedService
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
                        text = servicio.getSchedule().timeRange,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Place, contentDescription = "QTH", modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = servicio.getLocation().capitalizeFirst(),
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
                        text = "${servicio.getCantKm()} KM",
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
                    text = servicio.getDate(),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = servicio.getTypeService(),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Fiscalizado por :\n${servicio.getSupervised().getRank().toString().capitalizeFirst()} ${servicio.getSupervised().getLastName().capitalizeFirst()}",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DataServiceComponentPreview(){
    DataServiceComponent(
        servicio = CompletedService(
            lp = 7890,
            typeService = TYPESERVICE.CONSIGNA,
            date = "28/9/2025",
            schedule = SCHEDULE.MAÑANA,
            location = "av la plata",
            cantKm = 7.5f,
            supervised = PoliceDate(
                lp = 1234,
                lastName = "masi",
                firstName = "Juan Perez",
                rank = Rank.INSPECTOR,
                department = "policia",
                district = DISTRICT.C12,
                photoUrl = "foto"
            )
        )
    )
}