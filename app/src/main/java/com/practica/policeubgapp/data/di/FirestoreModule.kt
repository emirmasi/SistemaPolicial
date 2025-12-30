package com.practica.policeubgapp.data.di

import com.google.firebase.firestore.FirebaseFirestore
import com.practica.policeubgapp.data.network.FirestoreNetworkImplement
import com.practica.policeubgapp.data.network.FirestoreNetworkInterface
import com.practica.policeubgapp.data.repository.FirestoreRepositoryImplement
import com.practica.policeubgapp.data.repository.FirestoreRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {

    @Provides
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideFirestoreNetworkInterface(firestore: FirebaseFirestore): FirestoreNetworkInterface {
        return FirestoreNetworkImplement(firestore)
    }

    @Provides
    fun provideFirestoreRepositoryInterface(firestoreNetworkInterface: FirestoreNetworkInterface): FirestoreRepositoryInterface {
        return FirestoreRepositoryImplement(firestoreNetworkInterface)
    }


}