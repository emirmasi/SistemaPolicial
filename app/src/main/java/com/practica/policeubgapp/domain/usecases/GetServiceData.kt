package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.domain.models.CompletedServiceUI

interface GetServiceData {
    suspend fun getListServiceData(lp:String): List<CompletedServiceUI>
}
