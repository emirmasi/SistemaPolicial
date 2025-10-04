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
import com.practica.policeubgapp.domain.models.PoliceDate
import com.practica.policeubgapp.domain.models.Rank

@Composable
fun DataPoliceComponent(datePolice: PoliceDate){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CircleImage(datePolice.getPhotoUrl())
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextDataShowComponent("Apellido y nombre",datePolice.getLastName()+" "+datePolice.getFirstName())
            TextDataShowComponent("Jerarquia",datePolice.getRank().toString())
            TextDataShowComponent("Dependencia",datePolice.getDepartment())
            TextDataShowComponent("Comuna",datePolice.getDistrict().toString())
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DataPoliceComponentPreview(){
    DataPoliceComponent(PoliceDate(7960,"Masi","Isaias", Rank.INSPECTOR, department = "comisaria 3b",
        DISTRICT.C12,"https://i0.wp.com/www.palermomio.com.ar/wp-content/uploads/2017/01/PoliciaCiudadLogo.png?resize=250%2C187&ssl=1"))
}