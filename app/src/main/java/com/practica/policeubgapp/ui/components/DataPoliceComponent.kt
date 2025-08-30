package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DataPoliceComponent(){
    ///aca vamos a mostar los datos del efectivo
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CircleImage()
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextDataShowComponent("Apellido y nombre","masi Isaias")
            TextDataShowComponent("Jerarquia","oficial")
            TextDataShowComponent("Dependencia","comisaria 3b")
            TextDataShowComponent("Comuna",4)
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DataPoliceComponentPreview(){
    DataPoliceComponent()
}