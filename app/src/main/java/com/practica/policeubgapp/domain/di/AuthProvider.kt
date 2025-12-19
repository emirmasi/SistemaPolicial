package com.practica.policeubgapp.domain.di

import com.practica.policeubgapp.data.repository.AuthRepositoryInterface
import com.practica.policeubgapp.domain.services.AuthService
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import com.practica.policeubgapp.domain.usecases.SignInWithLpAndPassword
import com.practica.policeubgapp.domain.usecases.SignOut
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthProvider {

    @Provides
    fun provideAuthService(authRepository: AuthRepositoryInterface): AuthService {
        return AuthService(authRepository = authRepository)
    }

    @Provides
    fun provideSignInWithLpAndPassword(authService: AuthService): SignInWithLpAndPassword{
        return authService
    }
    @Provides
     fun provideGetCurrentUser(authService: AuthService): GetCurrentUser{
         return authService
     }

    @Provides
    fun provideSignOut(authService: AuthService): SignOut{
        return authService
    }
}