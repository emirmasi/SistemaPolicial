package com.practica.policeubgapp.data.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.practica.policeubgapp.domain.models.AuthRes

interface AuthNetworkFirebase {
    suspend fun signInWithEmailAndPassword(lp: String, password: String): AuthRes<FirebaseUser>
    suspend fun getCurrentUser(): FirebaseUser?
    suspend fun signOut()
}