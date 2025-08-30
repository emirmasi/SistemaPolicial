package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//aca deberia ir por estado un item de un servicio
@Composable
fun DataServiceComponent(){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(16.dp)
            .size(340.dp, 100.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = androidx.compose.ui.Alignment.Start,
            ) {
                Text(text = "Horario:14:00 a 22:00",
                    modifier = Modifier.padding(bottom = 6.dp),
                    fontSize = 12.sp
                )
                Text(text = "QTH:Av la plata y el libertador",
                    modifier = Modifier.padding(bottom = 6.dp),
                    maxLines = 2, // máximo 2 líneas
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp
                )
                Text(text = "cantKm:",
                    fontSize = 12.sp
                )
            }
            Column(
                horizontalAlignment = androidx.compose.ui.Alignment.End
            ) {
                Text(text = "22/08/2025",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(text = "Fiscalizado:",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DataServiceComponentPreview(){
    DataServiceComponent()
}