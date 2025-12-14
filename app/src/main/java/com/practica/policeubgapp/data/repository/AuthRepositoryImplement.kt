package com.practica.policeubgapp.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.practica.policeubgapp.data.network.AuthNetworkFirebase
import com.practica.policeubgapp.domain.models.AuthRes
import jakarta.inject.Inject

class AuthRepositoryImplement @Inject constructor(
    private val authNetworkFirebase: AuthNetworkFirebase
) : AuthRepositoryInterface {
    override suspend fun signInWithEmailAndPassword(
        lp: String,
        password: String
    ): AuthRes<FirebaseUser> {
        return authNetworkFirebase.signInWithEmailAndPassword(lp,password)
    }

    override suspend fun getCurrentUser(): FirebaseUser {
        TODO("Not yet implemented")
    }

}