package com.practica.policeubgapp.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.practica.policeubgapp.domain.models.Publicity
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreNetworkImplement @Inject constructor(
    private val firestore: FirebaseFirestore
) : FirestoreNetworkInterface {
    override suspend fun getPublicity(): List<Publicity> {
        return try {
            val snapshot = firestore.collection("publicidades")
                .whereEqualTo("active",true)
                .get()
                .await()
            snapshot.toObjects(Publicity::class.java)
        }catch (e: Exception){
            Log.e("FirestoreNetworkImplement", "Error al obtener publicidad", e)
                emptyList()
        }
    }

}