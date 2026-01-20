package com.practica.policeubgapp.data.mapped

import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.SCHEDULE
import com.practica.policeubgapp.domain.models.TYPESERVICE
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

fun PendingServiceModel.toUIModel(): PendingServiceUI {
    return PendingServiceUI(
        uid = this.uid,
        lp = this.lp,
        locationName = this.locationName,
        location = this.location,
        date = this.date.toLocalDateString(),
        // Conversión segura de String a Enum
        typeService = when (this.typeService.uppercase()) {
            "CONSIGNA" -> TYPESERVICE.CONSIGNA
            "UBG" -> TYPESERVICE.UBG
            "SATURACION" -> TYPESERVICE.SATURACION
            else -> TYPESERVICE.UBG // Un valor por defecto por seguridad
        },
        schedule = when (this.schedule.uppercase()) {
            "MAÑANA" -> SCHEDULE.MAÑANA
            "TARDE" -> SCHEDULE.TARDE
            "NOCHE" -> SCHEDULE.NOCHE
            else -> SCHEDULE.MAÑANA
        },
    )
}


fun Timestamp.toLocalDateString(): String {
    // Convertimos el Timestamp de Firebase a un Date de Java
    val date = this.toDate()

    // Definimos el formato: dd (día), MM (mes), yyyy (año)
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    return formatter.format(date)
}
