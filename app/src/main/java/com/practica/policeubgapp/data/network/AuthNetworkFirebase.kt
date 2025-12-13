package com.practica.policeubgapp.data.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthNetworkFirebase {
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult?
    suspend fun getCurrentUser(): FirebaseUser?
    suspend fun signOut()
}