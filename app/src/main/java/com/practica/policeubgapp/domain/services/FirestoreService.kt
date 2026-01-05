package com.practica.policeubgapp.domain.services

import com.practica.policeubgapp.data.repository.FirestoreRepositoryInterface
import com.practica.policeubgapp.domain.models.PendingService
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.domain.usecases.GetListOfServicePending
import com.practica.policeubgapp.domain.usecases.GetPublicity
import javax.inject.Inject

class FirestoreService @Inject constructor(
    private val firestore: FirestoreRepositoryInterface
) : GetPublicity, GetListOfServicePending{
    override suspend fun getPublicity(): List<Publicity> {
        return firestore.getPublicity()
    }

    override suspend fun getListOfServicePending(user: String?): List<PendingServiceUI> {
        return firestore.getListOfServicePending(user)
    }
}