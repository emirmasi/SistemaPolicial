package com.practica.policeubgapp.data.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint

data class ServiceCompletedModel(
    val lp: Int = 0,
    val date: Timestamp? = null , // Fecha del servicio
    val typeService: String = "",
    val schedule: String = "", // Tu enum de franja horaria (mañana/tarde/noche)
    val locationName: String = "",
    val geoPoint: GeoPoint? = null,
    val starTime: Timestamp? = null,     // Hora exacta que tocó "Iniciar"
    val endTime: Timestamp? = null,       // Hora exacta que tocó "Terminar"
    val totalHours: Float = 0f,       // Calculado (ej: 8.5 horas)
    val totalDistanceKm: Float = 0f
)