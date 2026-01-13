package com.practica.policeubgapp.domain.models


///clase publica para luego mostrar en la tarjeta del efectivo
class PoliceDateUI(
    val lp: Int,
    val lastName: String,
    val firstName: String,
    val rank: RANK,
    val department: String,
    val district: DISTRICT,
    val state: STATE,
    val photoUrl: String,
) {
    fun getRank(): String {
        return rank.name
    }
    fun getDistrict(): String {
        return district.name

    }
}