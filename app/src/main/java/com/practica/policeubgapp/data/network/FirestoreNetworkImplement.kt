package com.practica.policeubgapp.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.Hospital
import com.practica.policeubgapp.data.models.PendingServiceModel
import com.practica.policeubgapp.data.models.PoliceDateModel
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.PoliceDateUI
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.data.models.toUIModel
import com.practica.policeubgapp.data.models.toUiModel
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

    override suspend fun getListOfServicePending(user: String?): List<PendingServiceUI> {
        return try {
            val lpNumber = user?.substringBefore("@")?.toIntOrNull()
                ?: throw Exception("El LP proporcionado no es un número válido")

            val snapshot = firestore.collection("servicios")
                .whereEqualTo("lp",lpNumber)
                .get()
                .await()
            val list = snapshot.toObjects(PendingServiceModel::class.java)
            Log.e("firestoreNetworkImplemt","$list")
            list.map { it.toUIModel() }
        }catch (e : Exception){
            Log.e("firestoreNetworkImplemt","Error al obtener los servicios pendientes",e)
            emptyList()
        }
    }

    override suspend fun getComisarias(): List<Comisaria> {
        return try {
            val snapshot = firestore.collection("comisarias")
                .get()
                .await()
             snapshot.toObjects(Comisaria::class.java)
        }catch (e: Exception){
            Log.e("firestoreNetworkImplemt","Error al obtener las comisarias",e)
            emptyList()
        }
    }

    override suspend fun getHospitales(): List<Hospital> {
        return try {
            val snapshot = firestore.collection("hospitales")
                .get()
                .await()
            snapshot.toObjects(Hospital::class.java)
        }catch (e: Exception){
            Log.e("firestoreNetworkImplemt","Error al obtener los hospitales",e)
            emptyList()
        }
    }

    override suspend fun getPoliceDate(lp: String): PoliceDateUI {
        return try {
            val lpNumber = lp.toIntOrNull()
                ?: throw Exception("El LP proporcionado no es un número válido")
            val snapshot = firestore.collection("policias")
                .whereEqualTo("Lp", lpNumber) // O lp si es String en la DB
                .limit(1) // Optimización: pedimos solo uno
                .get()
                .await()
            if (!snapshot.isEmpty) {
                val policeModel = snapshot.documents[0].toObject(PoliceDateModel::class.java)
                policeModel?.toUiModel() ?: throw Exception("Datos de policía nulos")
            } else {
                throw Exception("No se encontró ningún policía con legajo: $lpNumber")
            }
        } catch (e: Exception) {
            Log.e("firestoreNetworkImplemt", "Error al obtener los datos de la policia", e)
            throw e // Es mejor lanzar la excepción para que el ViewModel la maneje
        }
    }
}