package com.practica.policeubgapp.data.network

import com.practica.policeubgapp.domain.models.Publicity

interface FirestoreNetworkInterface {
    suspend fun getPublicity(): List<Publicity>
}