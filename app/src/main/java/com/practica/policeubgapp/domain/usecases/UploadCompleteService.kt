package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.data.mapped.ServiceCompletedModel

interface UploadCompleteService {
    suspend fun uploadServiceComplete(service: ServiceCompletedModel): Result<Unit>
}