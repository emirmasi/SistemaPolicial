package com.practica.policeubgapp.domain.models

// Este lo usas para la lista general de hospitales (info de texto)
data class Hospital(
    val name: String = "",
    val address: String = "",
    val commune: Int = 0,
    val specialty: String = "",
    val type: String = ""
)

