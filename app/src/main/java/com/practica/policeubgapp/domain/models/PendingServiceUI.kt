package com.practica.policeubgapp.domain.models

import com.google.firebase.firestore.GeoPoint

data class PendingServiceUI(
    private val lp: String,
    private val typeService: TYPESERVICE,
    private val locationName: String,
    private val location: GeoPoint?,
    private val schedule: SCHEDULE,
    private val date: String
) {
    fun getTypeService(): TYPESERVICE{
        return typeService
    }
    fun getLocationName(): String{
        return locationName

    }
    fun getSchedule(): SCHEDULE{
        return schedule
    }
    fun getDate(): String{
        return date
    }
}