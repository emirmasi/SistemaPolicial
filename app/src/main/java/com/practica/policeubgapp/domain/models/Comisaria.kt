package com.practica.policeubgapp.domain.models

import com.google.firebase.firestore.GeoPoint

data class Comisaria(
    val id: Int = 0,
    val name: String = "",
    val address: String = "",
    val neighborhood: String = "",
    val commune: Int = 0,
    val phone: String = "",
    val location: GeoPoint? = null,
    val type: String = ""
)
