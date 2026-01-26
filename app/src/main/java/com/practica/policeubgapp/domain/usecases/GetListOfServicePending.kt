package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.domain.models.PendingServiceUI
import kotlinx.coroutines.flow.Flow

interface GetListOfServicePending {
    suspend fun getListOfServicePending(user: String?): Flow<List<PendingServiceUI>>
}


