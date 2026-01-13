package com.practica.policeubgapp.data.models

import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.SCHEDULE
import com.practica.policeubgapp.domain.models.TYPESERVICE


fun PendingServiceModel.toUIModel(): PendingServiceUI {
    return PendingServiceUI(
        lp = this.lp,
        locationName = this.locationName,
        location = this.location,
        date = this.date,
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