package com.practica.policeubgapp.domain.services

import com.practica.policeubgapp.data.repository.FirestoreRepositoryInterface
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.Hospital
import com.practica.policeubgapp.domain.models.PendingService
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.domain.usecases.GetComisarias
import com.practica.policeubgapp.domain.usecases.GetHospitales
import com.practica.policeubgapp.domain.usecases.GetListOfServicePending
import com.practica.policeubgapp.domain.usecases.GetPublicity
import javax.inject.Inject

class FirestoreService @Inject constructor(
    private val firestore: FirestoreRepositoryInterface
) : GetPublicity, GetListOfServicePending, GetComisarias, GetHospitales{
    override suspend fun getPublicity(): List<Publicity> {
        return firestore.getPublicity()
    }

    override suspend fun getListOfServicePending(user: String?): List<PendingServiceUI> {
        return firestore.getListOfServicePending(user)
    }

    override suspend fun getComisiarias(): List<Comisaria> {
        return firestore.getComisarias()
    }

    override suspend fun getHospitales(): List<Hospital> {
        return firestore.getHospitales()
    }
}