package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.domain.models.Hospital

interface GetHospitales {
    suspend fun getHospitales(): List<Hospital>
}