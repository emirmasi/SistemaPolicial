package com.practica.policeubgapp.domain.models

import com.google.firebase.firestore.GeoPoint

data class MarkerData(
    val name: String = "",
    val location: GeoPoint? = null,
    val type: String = ""
)
