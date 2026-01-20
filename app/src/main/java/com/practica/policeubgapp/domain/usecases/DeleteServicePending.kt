package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.domain.models.AuthRes

interface DeleteServicePending {
    suspend fun deleteServicePending(uid:String): Result<Unit>
}