package com.practica.policeubgapp.domain.models

class PoliceDate(
    private val lp: Int,
    private val lastName: String,
    private val firstName: String,
    private val rank: Rank,
    private val department: String,
    private val district: DISTRICT,
    private val photoUrl: String,
) {
    fun getLastName(): String {
        return lastName
    }
    fun getFirstName(): String {
        return firstName
    }
    fun getRank(): Rank {
        return rank
    }
    fun getLp(): Int {
        return lp
    }
    fun getDepartment(): String {
        return department
    }
    fun getDistrict(): DISTRICT {
        return district

    }
    fun getPhotoUrl(): String {
        return photoUrl
    }
}