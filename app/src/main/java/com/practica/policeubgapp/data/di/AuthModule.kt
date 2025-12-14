package com.practica.policeubgapp.data.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.practica.policeubgapp.data.network.AuthNetworkFirebase
import com.practica.policeubgapp.data.network.AuthNetworkImplement
import com.practica.policeubgapp.data.repository.AuthRepositoryImplement
import com.practica.policeubgapp.data.repository.AuthRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    fun providesFirebaseAuth() = Firebase.auth

    @Provides
    fun provideAuthNetworkInterface(auth: FirebaseAuth): AuthNetworkFirebase {
        return AuthNetworkImplement(auth)
    }

    @Provides
    fun provideAuthRepositoryInterface(authNetworkFirebase: AuthNetworkFirebase): AuthRepositoryInterface {
        return AuthRepositoryImplement(authNetworkFirebase)
    }
}