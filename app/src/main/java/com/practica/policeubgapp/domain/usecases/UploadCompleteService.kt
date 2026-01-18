package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.domain.models.CompletedServiceUI

interface UploadCompleteService {
    suspend fun uploadServiceComplete(service: CompletedServiceUI): Result<Unit>
}