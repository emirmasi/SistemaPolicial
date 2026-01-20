package com.practica.policeubgapp.data.network

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.practica.policeubgapp.data.mapped.PendingServiceModel
import com.practica.policeubgapp.data.mapped.PoliceDateModel
import com.practica.policeubgapp.data.mapped.ServiceCompletedModel
import com.practica.policeubgapp.data.mapped.toUIModel
import com.practica.policeubgapp.data.mapped.toUiModel
import com.practica.policeubgapp.domain.models.AuthRes
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.CompletedServiceUI
import com.practica.policeubgapp.domain.models.Hospital
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.PoliceDateUI
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

    override suspend fun getListOfServicePending(user: String?): List<PendingServiceUI> {
        return try {
            // 1. Extraemos el LP del correo
            val lpNumber = user?.substringBefore("@")?.toIntOrNull()
                ?: throw Exception("El LP proporcionado no es un número válido")

            // 2. Realizamos la consulta filtrada
            val snapshot = firestore.collection("servicios")
                .whereEqualTo("lp", lpNumber)
                .get()
                .await()

            // 3. Mapeamos documento por documento para capturar el ID
            val list = snapshot.documents.mapNotNull { document ->
                // Convertimos el contenido a tu modelo de datos
                val model = document.toObject(PendingServiceModel::class.java)

                // Inyectamos el ID del documento en el campo 'uid' del modelo
                // Usamos .copy() para no perder el resto de los datos
                model?.copy(uid = document.id)?.toUIModel()
            }
            Log.d("firestoreNetwork", "Servicios obtenidos: ${list.size}")
            list
        } catch (e: Exception) {
            Log.e("firestoreNetwork", "Error al obtener los servicios pendientes", e)
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
            Log.e("firestoreNetworkImplement","Error al obtener los hospitales",e)
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

    override suspend fun getListServiceData(lp: String): List<CompletedServiceUI> {
        val lpNumber = lp.toIntOrNull()
            ?: throw Exception("El LP: $lp proporcionado no es un número válido")

        return try {
            val snapshot = firestore.collection("servicios_completados")
                .whereEqualTo("lp", lpNumber)
                .get()
                .await()
            val list = snapshot.toObjects(ServiceCompletedModel::class.java)
            Log.e("firestoreNetworkImplemt","$list")
            list.map { it.toUIModel() }
        }catch (e: Exception){
            Log.e("firestoreNetworkImplemt","Error al obtener los servicios completados",e)
            emptyList()
        }

    }

    override suspend fun uploadServiceComplete(service: CompletedServiceUI): Result<Unit> {
        return try {
            firestore.collection("servicios_completados")
                .add(service) // Aquí pasas tu modelo denormalizado
                .await() // Esperamos a que termine la subida

            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("UploadService", "Error al subir: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun deleteServicePending(uid: String): Result<Unit> {
        return try {
            firestore.collection("servicios")
                .document(uid)
                .delete()
                .await()
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("deleteServicePending", "Error al eliminar: ${e.message}")
            Result.failure(e)
        }
    }
}