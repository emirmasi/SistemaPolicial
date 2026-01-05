package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.domain.models.PendingService
import com.practica.policeubgapp.domain.models.PendingServiceUI

interface GetListOfServicePending {
    suspend fun getListOfServicePending(user: String?): List<PendingServiceUI>
}


