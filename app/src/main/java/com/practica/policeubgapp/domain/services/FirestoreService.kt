package com.practica.policeubgapp.domain.services

import com.practica.policeubgapp.data.repository.FirestoreRepositoryInterface
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.domain.usecases.GetPublicity
import javax.inject.Inject

class FirestoreService @Inject constructor(
    private val firestore: FirestoreRepositoryInterface
) : GetPublicity{
    override suspend fun getPublicity(): List<Publicity> {
        return firestore.getPublicity()
    }
}