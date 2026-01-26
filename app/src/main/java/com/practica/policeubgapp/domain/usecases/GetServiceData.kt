package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.domain.models.CompletedServiceUI
import kotlinx.coroutines.flow.Flow

interface GetServiceData {
    suspend fun getListServiceData(lp:String): Flow<List<CompletedServiceUI>>
}
