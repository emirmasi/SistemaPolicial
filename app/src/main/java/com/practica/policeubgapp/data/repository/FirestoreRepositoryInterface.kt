package com.practica.policeubgapp.data.repository

import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.Hospital
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.PoliceDateUI
import com.practica.policeubgapp.domain.models.Publicity

interface FirestoreRepositoryInterface {
    suspend fun getPublicity():List<Publicity>
    suspend fun getListOfServicePending(user: String?): List<PendingServiceUI>
    suspend fun getComisarias(): List<Comisaria>
    suspend fun getHospitales(): List<Hospital>
    suspend fun getPoliceDate(lp: String): PoliceDateUI
}
