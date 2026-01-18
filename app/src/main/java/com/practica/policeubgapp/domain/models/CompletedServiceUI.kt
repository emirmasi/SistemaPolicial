package com.practica.policeubgapp.domain.models

import com.google.firebase.firestore.GeoPoint

data class CompletedServiceUI(
     val lp: Int,
     val date: String, // Fecha del servicio
     val typeService: TYPESERVICE,
     val schedule: SCHEDULE, // Tu enum de franja horaria (mañana/tarde/noche)
     val locationName: String,
     val geoPoint: GeoPoint?,
     val startTime: String,     // Hora exacta que tocó "Iniciar"
     val endTime: String,       // Hora exacta que tocó "Terminar"
     val totalHours: Float,       // Calculado (ej: 8.5 horas)
     val totalDistanceKm: Float    // Calculado por GPS
)