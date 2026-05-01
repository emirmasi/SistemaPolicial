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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica.policeubgapp.R
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.SCHEDULE
import com.practica.policeubgapp.domain.models.TYPESERVICE
import com.practica.policeubgapp.domain.models.capitalizeFirst

@Composable
fun TargetPendingServiceComponent(
    service: PendingServiceUI,
    onClickDetail: () -> Unit,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp, vertical = 5.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "ESTADO: ASIGNADO",
                modifier = Modifier.align(Alignment.End),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            TextTarjetService(
                icon = R.drawable.baseline_hourglass_bottom_24,
                placeholder = "Horario:",
                text = service.schedule.timeRange
            )
            TextTarjetService(
                icon = R.drawable.baseline_place_24,
                placeholder = "Ubicacion",
                text = service.locationName
            )

            TextTarjetService(
                icon = R.drawable.baseline_calendar_month_24,
                placeholder = "Fecha",
                text = service.date
            )
            TextTarjetService(
                icon = R.drawable.baseline_workspace_premium_24,
                placeholder = "Tipo de Servicio",
                text = service.typeService.name
            )

            ElevatedButton(
                onClick = {
                    onClickDetail()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = MaterialTheme.shapes.small,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Mas detalles")
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun ShowTargetPreview(){
    TargetPendingServiceComponent(
        PendingServiceUI(
            uid = "1",
            lp = 6252,
            typeService = TYPESERVICE.UBG,
            locationName = "av segurola y la plata",
            location = null,
            schedule = SCHEDULE.TARDE,
            date = "20/05/2023"
        )
    ){

    }
}