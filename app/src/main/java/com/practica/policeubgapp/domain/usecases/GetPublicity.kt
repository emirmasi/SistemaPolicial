package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.domain.models.Publicity


interface GetPublicity {
    suspend fun getPublicity(): List<Publicity>
}

