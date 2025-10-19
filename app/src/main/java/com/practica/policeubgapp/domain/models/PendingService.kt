package com.practica.policeubgapp.domain.models

class PendingService(
    private val lp:Int,
    private val typeService: TYPESERVICE,
    private val location: String,
    private val schedule: SCHEDULE,
    private val date: String,
) {
    fun getLp(): Int {
        return lp
    }
    fun getTypeService(): String {
        return typeService.toString()
    }
    fun getLocation(): String {
        return location
    }
    fun getSchedule(): SCHEDULE {
        return schedule
    }
    fun getDate(): String {
        return date
    }

}