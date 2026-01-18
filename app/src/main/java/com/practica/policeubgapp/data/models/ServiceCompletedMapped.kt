package com.practica.policeubgapp.data.models

import com.google.firebase.Timestamp
import com.practica.policeubgapp.domain.models.CompletedServiceUI
import com.practica.policeubgapp.domain.models.SCHEDULE
import com.practica.policeubgapp.domain.models.TYPESERVICE
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun ServiceCompletedModel.toUIModel(): CompletedServiceUI{
    return CompletedServiceUI(
        lp = this.lp,
        date = this.date?.toLocalDateString()?: "00:00",
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
        locationName = this.locationName,
        geoPoint = this.geoPoint,
        startTime = this.starTime?.toLocalTimeString()?:"00:00",
        endTime = this.endTime?.toLocalTimeString()?:"00:00",
        totalHours = this.totalHours,
        totalDistanceKm = this.totalDistanceKm
    )
}

fun Timestamp.toLocalTimeString(): String {
    val date = this.toDate()

    // "HH" es formato 0-23 horas, "mm" son los minutos
    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
    formatter.timeZone = TimeZone.getTimeZone("America/Argentina/Buenos_Aires")
    return formatter.format(date)
}