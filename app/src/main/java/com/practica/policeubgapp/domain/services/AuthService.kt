package com.practica.policeubgapp.domain.services

import com.google.firebase.auth.FirebaseUser
import com.practica.policeubgapp.data.repository.AuthRepositoryInterface
import com.practica.policeubgapp.domain.models.AuthRes
import com.practica.policeubgapp.domain.usecases.SignInWithLpAndPassword
import javax.inject.Inject

class AuthService @Inject constructor(
    private val authRepository: AuthRepositoryInterface
): SignInWithLpAndPassword {
    override suspend fun signInWithLpAndPassword(lp: String, password: String): AuthRes<FirebaseUser> {
        return authRepository.signInWithEmailAndPassword(lp,password)
    }

}