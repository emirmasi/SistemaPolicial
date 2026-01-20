package com.practica.policeubgapp.domain.models

import com.google.firebase.firestore.GeoPoint

data class PendingServiceUI(
    val uid: String,
    val lp: Int,
    val typeService: TYPESERVICE,
    val locationName: String,
    val location: GeoPoint?,
    val schedule: SCHEDULE,
    val date: String
)
