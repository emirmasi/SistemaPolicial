package com.practica.policeubgapp.domain.models

class CompletedService(
    private val lp: Int,
    private val date:String,
    private val typeService: TYPESERVICE,
    private val schedule: SCHEDULE,
    private val location: String,
    private val cantKm: Float,
    private val supervised: PoliceDateUI
) {
    fun getLp(): Int {
        return lp
    }
    fun getDate(): String {
        return date
    }
    fun getTypeService(): String {
        return typeService.toString().capitalizeFirst()
    }
    fun getSchedule(): SCHEDULE {
        return schedule
    }
    fun getLocation(): String {
        return location
    }
    fun getCantKm(): Float {
        return cantKm
    }
    fun getSupervised(): PoliceDateUI {
        return supervised
    }

}