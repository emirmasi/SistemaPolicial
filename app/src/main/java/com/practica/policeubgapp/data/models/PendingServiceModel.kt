package com.practica.policeubgapp.data.models

import com.google.firebase.firestore.GeoPoint

data class PendingServiceModel(
    val lp: Int = 0,
    val typeService: String = "",
    val locationName: String = "",
    val location: GeoPoint? = null,
    val schedule: String = "",
    val date: String = "",
)