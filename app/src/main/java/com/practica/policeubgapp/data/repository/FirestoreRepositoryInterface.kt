package com.practica.policeubgapp.data.repository

import com.practica.policeubgapp.domain.models.Publicity

interface FirestoreRepositoryInterface {
    suspend fun getPublicity():List<Publicity>
}
