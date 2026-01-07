package com.practica.policeubgapp.data.repository

import com.practica.policeubgapp.data.network.FirestoreNetworkInterface
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.PendingService
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.Publicity
import javax.inject.Inject

class FirestoreRepositoryImplement @Inject constructor(
    private val firestoreNetworkInterface: FirestoreNetworkInterface
): FirestoreRepositoryInterface {
    override suspend fun getPublicity(): List<Publicity> {
        return firestoreNetworkInterface.getPublicity()
    }

    override suspend fun getListOfServicePending(user: String?): List<PendingServiceUI> {
        return firestoreNetworkInterface.getListOfServicePending(user)
    }

    override suspend fun getComisarias(): List<Comisaria> {
        return firestoreNetworkInterface.getComisarias()

    }

}