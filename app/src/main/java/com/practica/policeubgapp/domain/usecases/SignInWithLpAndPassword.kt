package com.practica.policeubgapp.domain.usecases

import com.google.firebase.auth.FirebaseUser
import com.practica.policeubgapp.domain.models.AuthRes

interface SignInWithLpAndPassword {
    suspend fun signInWithLpAndPassword(lp: String, password:String): AuthRes<FirebaseUser>
}