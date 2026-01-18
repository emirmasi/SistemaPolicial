package com.practica.policeubgapp.domain.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint

//este date lo tengo que cambiar por timeStamp

data class PendingServiceUI(
    val lp: Int,
    val typeService: TYPESERVICE,
    val locationName: String,
    val location: GeoPoint?,
    val schedule: SCHEDULE,
    val date: String
)
