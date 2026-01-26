package com.practica.policeubgapp.domain.di

import com.practica.policeubgapp.data.repository.FirestoreRepositoryInterface
import com.practica.policeubgapp.domain.services.FirestoreService
import com.practica.policeubgapp.domain.usecases.DeleteServicePending
import com.practica.policeubgapp.domain.usecases.GetComisarias
import com.practica.policeubgapp.domain.usecases.GetHospitales
import com.practica.policeubgapp.domain.usecases.GetListOfServicePending
import com.practica.policeubgapp.domain.usecases.GetPoliceDate
import com.practica.policeubgapp.domain.usecases.GetPublicity
import com.practica.policeubgapp.domain.usecases.GetServiceData
import com.practica.policeubgapp.domain.usecases.UploadCompleteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirestoreProvider {
    @Provides
    fun provideFirestoreService(firestoreRepository: FirestoreRepositoryInterface): FirestoreService {
        return FirestoreService(firestoreRepository)
    }
    @Provides
    fun provideGetPublicity(firestoreService: FirestoreService): GetPublicity {
        return firestoreService
    }
    @Provides
    fun provideGetListOfServicePending(firestoreService: FirestoreService): GetListOfServicePending {
        return firestoreService
    }
    @Provides
    fun provideGetComisarias(firestoreService: FirestoreService): GetComisarias {
        return firestoreService
    }
    @Provides
    fun provideGetHospitales(firestoreService: FirestoreService): GetHospitales {
        return firestoreService
    }

    @Provides
    fun provideGetPoliceDate(firestoreService: FirestoreService): GetPoliceDate {
        return firestoreService

    }
    @Provides
    fun provideGetServiceData(firestoreService: FirestoreService): GetServiceData {
        return firestoreService

    }
    @Provides
    fun provideUploadCompleteService(firestoreService: FirestoreService): UploadCompleteService {
        return firestoreService
    }
    @Provides
    fun provideDeleteServicePending(firestoreService: FirestoreService): DeleteServicePending {
        return firestoreService
    }

}