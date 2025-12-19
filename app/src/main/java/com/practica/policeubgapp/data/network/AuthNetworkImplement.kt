package com.practica.policeubgapp.data.network

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.practica.policeubgapp.domain.models.AuthRes
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthNetworkImplement @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthNetworkFirebase{
    override suspend fun signInWithEmailAndPassword(
        lp: String,
        password: String
    ): AuthRes<FirebaseUser> =
        try {
            val result = firebaseAuth.signInWithEmailAndPassword(lp.toString(),password).await()
            val firebaseUser = result.user
            if(firebaseUser != null){
                AuthRes.Succes(firebaseUser)
            }else{
                AuthRes.Error("authentication failed")
            }
        }catch (e: Exception){
            AuthRes.Error(e.message?: "An Error occurred while loging in")
        }

    override suspend fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override suspend fun signOut() {
        firebaseAuth.signOut()
    }
}