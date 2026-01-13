package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.practica.policeubgapp.domain.models.DISTRICT
import com.practica.policeubgapp.domain.models.PoliceDateUI
import com.practica.policeubgapp.domain.models.RANK
import com.practica.policeubgapp.domain.models.STATE

@Composable
fun DataPoliceComponent(datePolice: PoliceDateUI){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CircleImage(datePolice.photoUrl)
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextDataShowComponent("Apellido y nombre",datePolice.lastName+" "+datePolice.firstName)
            TextDataShowComponent("Jerarquia",datePolice.getRank())
            TextDataShowComponent("Dependencia",datePolice.department)
            TextDataShowComponent("Comuna",datePolice.getDistrict())
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DataPoliceComponentPreview(){
    DataPoliceComponent(PoliceDateUI(7960,"Masi","Isaias", RANK.INSPECTOR, department = "comisaria 3b",
        DISTRICT.C12, STATE.Efectivo,"https://i0.wp.com/www.palermomio.com.ar/wp-content/uploads/2017/01/PoliciaCiudadLogo.png?resize=250%2C187&ssl=1"))
}