package com.practica.policeubgapp.domain.usecases

import com.google.firebase.auth.FirebaseUser

interface GetCurrentUser {
    suspend fun getCurrentUser(): FirebaseUser?
}
