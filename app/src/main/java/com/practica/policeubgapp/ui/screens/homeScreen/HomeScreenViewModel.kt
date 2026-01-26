package com.practica.policeubgapp.ui.screens.homeScreen

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.Timestamp
import com.practica.policeubgapp.data.location.LocationService
import com.practica.policeubgapp.data.mapped.ServiceCompletedModel
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.domain.usecases.DeleteServicePending
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import com.practica.policeubgapp.domain.usecases.GetListOfServicePending
import com.practica.policeubgapp.domain.usecases.GetPublicity
import com.practica.policeubgapp.domain.usecases.UploadCompleteService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val publicityUseCase: GetPublicity,
    private val getListOfService: GetListOfServicePending,
    private val getCurrentUser: GetCurrentUser,
    private val deleteServicePending: DeleteServicePending,
    private val uploadServiceComplete: UploadCompleteService
) : ViewModel() {
    private val _publicity = MutableLiveData<List<Publicity>>()
    val publicity: LiveData<List<Publicity>> = _publicity
    private val _listOfServicies = MutableStateFlow<List<PendingServiceUI>>(emptyList())
    val listOfServices: StateFlow<List<PendingServiceUI>> = _listOfServicies
    private val _selectedService = MutableStateFlow<PendingServiceUI?>(null)
    val selectedServiceUI: StateFlow<PendingServiceUI?> = _selectedService
    var currentPosition by mutableStateOf<LatLng?>(null)

    private var startTime: Timestamp? = Timestamp.now()
    private var endTime: Timestamp? = Timestamp.now()
    private var lastLocation: Location? = null
    ///cuando se incia la viewModel vamos a traer las publicidades
    init {
      loadAllData()
        viewModelScope.launch {
            LocationService.locationData.collect { newLocation ->
                updateLocation(newLocation)
            }
        }
    }
    fun loadAllData(){
        getPublicity()
        viewModelScope.launch {
            val userEmail = getCurrentUser.getCurrentUser()?.email
           if(userEmail!= null){
                getListOfService(userEmail)
           }else {
               // Opcional: Reintentar después de un pequeño delay o manejar error
               Log.e("HomeVM", "Usuario no encontrado al iniciar")
           }

        }
    }

    private fun getListOfService(user: String){
        viewModelScope.launch {
            try {
                 getListOfService.getListOfServicePending(user).collect {  nuevaLista->
                    _listOfServicies.value =  nuevaLista
                }
            }catch (e: Exception) {
                Log.e("no se pudo traer los servicios pendientes", e.message.toString())
                _listOfServicies.value = emptyList()
            }
        }
    }

    private fun getPublicity() {
        viewModelScope.launch {
            try {
                val response = publicityUseCase.getPublicity()
                _publicity.value = response
            } catch (e: Exception) {
                Log.e("no se pudo traer las publicidades", e.message.toString())
                _publicity.value = emptyList()
            }
        }
    }
    fun starTime() {
        startTime = Timestamp.now()
    }

    fun endTime() {
        endTime = Timestamp.now()
    }
    fun deleteService(uid: String) {
        viewModelScope.launch {
            deleteServicePending.deleteServicePending(uid)
        }
    }
    fun updateLocation(newLocation: Location) {
        val newLatLng = LatLng(newLocation.latitude, newLocation.longitude)
        // 2. Actualizar estados
        lastLocation = newLocation
        currentPosition = newLatLng
    }
    fun setSelectedPendingUI(service: PendingServiceUI?){
        _selectedService.value = service
    }

    fun getSelectedPendingUI(): PendingServiceUI? {
        return selectedServiceUI.value
    }

    @SuppressLint("DefaultLocale")
    fun getDistanceKm(): String {
        val km = LocationService.distanciaAcumulada
        return String.format("%.2f", km)
    }


    fun timestampeToHours(startTime: Timestamp?, endTime: Timestamp?): Float {
        if (startTime == null || endTime == null) {
            return 0f
        } else {
            val startTimeMillis = startTime.toDate().time
            val endTimeMillis = endTime.toDate().time
            val timeDifferenceMillis = endTimeMillis - startTimeMillis
            val hours = timeDifferenceMillis.toFloat() / (1000 * 60 * 60)
            // Opcional: Redondear a 2 decimales para que no sea muy largo (ej: 8.25)
            return String.format(Locale.US, "%.2f", hours).toFloat()
        }
    }

    fun stringToTimeStamp(time: String): Timestamp? {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val date = sdf.parse(time)
            date?.let { Timestamp(it) }
        } catch (e: Exception) {
            null
        }
    }
    fun resetDistance() {
        LocationService.distanciaAcumulada = 0f
    }

    fun uploadCompletedService() {
        ///creo un completed service
        val distance = LocationService.distanciaAcumulada
        resetDistance()
        val completedService = ServiceCompletedModel(
            lp = selectedServiceUI.value?.lp ?: 0,
            date = stringToTimeStamp(selectedServiceUI.value?.date ?: ""),
            typeService = selectedServiceUI.value?.typeService?.name ?: "",
            schedule = selectedServiceUI.value?.schedule?.name ?: "",
            locationName = selectedServiceUI.value?.locationName ?: "",
            geoPoint = selectedServiceUI.value?.location,
            starTime = this.startTime,
            endTime = this.endTime,
            totalHours = timestampeToHours(this.startTime, this.endTime),
            totalDistanceKm = distance
        )
        viewModelScope.launch {
            try {
                uploadServiceComplete.uploadServiceComplete(completedService)
                LocationService.distanciaAcumulada = 0f
            } catch (e: Exception) {
                Log.e("no se pudo subir el servicio", e.message.toString())
            }
        }
    }
}