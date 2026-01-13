package com.practica.policeubgapp.domain.di

import com.practica.policeubgapp.data.repository.FirestoreRepositoryInterface
import com.practica.policeubgapp.domain.services.FirestoreService
import com.practica.policeubgapp.domain.usecases.GetComisarias
import com.practica.policeubgapp.domain.usecases.GetHospitales
import com.practica.policeubgapp.domain.usecases.GetListOfServicePending
import com.practica.policeubgapp.domain.usecases.GetPoliceDate
import com.practica.policeubgapp.domain.usecases.GetPublicity
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

}