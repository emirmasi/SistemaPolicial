package com.practica.policeubgapp.data.network

import com.practica.policeubgapp.domain.models.AuthRes
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.CompletedServiceUI
import com.practica.policeubgapp.domain.models.Hospital
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.PoliceDateUI
import com.practica.policeubgapp.domain.models.Publicity

interface FirestoreNetworkInterface {
    suspend fun getPublicity(): List<Publicity>
    suspend fun getListOfServicePending(user: String?): List<PendingServiceUI>
    suspend fun getComisarias(): List<Comisaria>
    suspend fun getHospitales(): List<Hospital>
    suspend fun getPoliceDate(lp: String): PoliceDateUI

    suspend fun getListServiceData(lp: String): List<CompletedServiceUI>
    suspend fun uploadServiceComplete(service: CompletedServiceUI): Result<Unit>
    suspend fun deleteServicePending(uid: String): Result<Unit>
}
