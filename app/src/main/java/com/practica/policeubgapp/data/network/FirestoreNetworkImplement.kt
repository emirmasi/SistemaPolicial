package com.practica.policeubgapp.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.practica.policeubgapp.domain.models.PendingService
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.domain.models.toUIModel
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.math.log

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

    override suspend fun getListOfServicePending(user: String?): List<PendingServiceUI> {
        return try {
            val snapshot = firestore.collection("servicios")
                .whereEqualTo("lp",user)
                .get()
                .await()
            val list = snapshot.toObjects(PendingService::class.java)
            Log.e("firestoreNetworkImplemt","$list")
            list.map { it.toUIModel() }
        }catch (e : Exception){
            Log.e("firestoreNetworkImplemt","Error al obtener los servicios pendientes",e)
            emptyList()
        }
    }
}