package com.practica.policeubgapp.domain.models

enum class SCHEDULE(val timeRange: String) {
    MAÑANA("06:00 a 14:00"),
    TARDE("14:00 a 22:00"),
    NOCHE("22:00 a 06:00")
}
