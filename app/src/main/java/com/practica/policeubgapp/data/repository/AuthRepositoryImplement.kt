package com.practica.policeubgapp.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.practica.policeubgapp.data.network.AuthNetworkFirebase
import jakarta.inject.Inject

class AuthRepositoryImplement @Inject constructor(
    private val authNetworkFirebase: AuthNetworkFirebase
) : AuthRepository {
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult? {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): FirebaseUser {
        TODO("Not yet implemented")
    }

}