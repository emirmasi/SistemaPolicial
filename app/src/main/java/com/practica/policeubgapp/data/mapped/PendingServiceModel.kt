package com.practica.policeubgapp.data.mapped

import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint

data class PendingServiceModel(
    val uid: String = "",
    val lp: Int = 0,
    val typeService: String = "",
    val locationName: String = "",
    val location: GeoPoint? = null,
    val schedule: String = "",
    val date: Timestamp = Timestamp.now()
)