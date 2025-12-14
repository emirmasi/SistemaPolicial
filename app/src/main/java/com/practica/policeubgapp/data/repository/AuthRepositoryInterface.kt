package com.practica.policeubgapp.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.practica.policeubgapp.domain.models.AuthRes

interface AuthRepositoryInterface {
    suspend fun signInWithEmailAndPassword(lp: String, password: String): AuthRes<FirebaseUser>
    suspend fun getCurrentUser(): FirebaseUser
}