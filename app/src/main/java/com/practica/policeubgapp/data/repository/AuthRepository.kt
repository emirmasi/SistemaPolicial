package com.practica.policeubgapp.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult?
    suspend fun getCurrentUser(): FirebaseUser
}