package com.practica.policeubgapp.domain.models

import com.google.firebase.firestore.GeoPoint

data class PendingService(
    val lp: String = "",
    val typeService: String = "",
    val locationName: String = "",
    val location: GeoPoint? = null ,
    val schedule: String = "",
    val date: String = "",
)