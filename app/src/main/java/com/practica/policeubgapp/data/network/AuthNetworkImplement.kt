package com.practica.policeubgapp.data.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthNetworkImplement @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthNetworkFirebase{
    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): AuthResult? {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUser(): FirebaseUser? {
        TODO("Not yet implemented")
    }

    override suspend fun signOut() {
        TODO("Not yet implemented")
    }
}