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
import com.practica.policeubgapp.domain.models.COMUNA
import com.practica.policeubgapp.domain.models.DatePolice
import com.practica.policeubgapp.domain.models.JERARQUIA

@Composable
fun DataPoliceComponent(datePolice: DatePolice){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CircleImage(datePolice.getFoto())
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextDataShowComponent("Apellido y nombre",datePolice.getApellido()+" "+datePolice.getNombre())
            TextDataShowComponent("Jerarquia",datePolice.getJerarquia().toString())
            TextDataShowComponent("Dependencia",datePolice.getDependencia())
            TextDataShowComponent("Comuna",datePolice.getComuna().toString())
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DataPoliceComponentPreview(){
    DataPoliceComponent(DatePolice(7960,"Masi","Isaias", JERARQUIA.INSPECTOR, dependecia = "comisaria 3b",
        COMUNA.C12,"https://i0.wp.com/www.palermomio.com.ar/wp-content/uploads/2017/01/PoliciaCiudadLogo.png?resize=250%2C187&ssl=1"))
}