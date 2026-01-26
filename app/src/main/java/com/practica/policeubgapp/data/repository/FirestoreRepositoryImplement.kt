package com.practica.policeubgapp.data.repository

import com.practica.policeubgapp.data.mapped.ServiceCompletedModel
import com.practica.policeubgapp.data.network.FirestoreNetworkInterface
import com.practica.policeubgapp.domain.models.AuthRes
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.CompletedServiceUI
import com.practica.policeubgapp.domain.models.Hospital
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.PoliceDateUI
import com.practica.policeubgapp.domain.models.Publicity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FirestoreRepositoryImplement @Inject constructor(
    private val firestoreNetworkInterface: FirestoreNetworkInterface
): FirestoreRepositoryInterface {
    override suspend fun getPublicity(): List<Publicity> {
        return firestoreNetworkInterface.getPublicity()
    }

    override suspend fun getListOfServicePending(user: String?): Flow<List<PendingServiceUI>> {
        return firestoreNetworkInterface.getListOfServicePending(user)
    }

    override suspend fun getComisarias(): List<Comisaria> {
        return firestoreNetworkInterface.getComisarias()

    }

    override suspend fun getHospitales(): List<Hospital> {
        return firestoreNetworkInterface.getHospitales()
    }

    override suspend fun getPoliceDate(lp: String): PoliceDateUI {
        return firestoreNetworkInterface.getPoliceDate(lp)
    }

    override suspend fun getListServiceData(lp: String): Flow<List<CompletedServiceUI>> {
        return firestoreNetworkInterface.getListServiceData(lp)

    }

    override suspend fun uploadServiceComplete(service: ServiceCompletedModel): Result<Unit> {
        return firestoreNetworkInterface.uploadServiceComplete(service)

    }

    override suspend fun deleteServicePending(uid: String): Result<Unit> {
        return firestoreNetworkInterface.deleteServicePending(uid)
    }

}