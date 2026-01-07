package com.practica.policeubgapp.data.network

import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.PendingService
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.Publicity

interface FirestoreNetworkInterface {
    suspend fun getPublicity(): List<Publicity>
    suspend fun getListOfServicePending(user: String?): List<PendingServiceUI>
    suspend fun getComisarias(): List<Comisaria>

}