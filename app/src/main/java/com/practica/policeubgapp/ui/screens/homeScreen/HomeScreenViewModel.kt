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
import com.practica.policeubgapp.data.location.LocationService
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.domain.usecases.DeleteServicePending
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import com.practica.policeubgapp.domain.usecases.GetListOfServicePending
import com.practica.policeubgapp.domain.usecases.GetPublicity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val publicityUseCase: GetPublicity,
    private val getListOfService: GetListOfServicePending,
    private val getCurrentUser: GetCurrentUser,
    private val deleteServicePending: DeleteServicePending
) : ViewModel() {
    private val _publicity = MutableLiveData<List<Publicity>>()
    val publicity: LiveData<List<Publicity>> = _publicity
    private val _listOfServicies = MutableLiveData<List<PendingServiceUI>>()
    val listOfServices: LiveData<List<PendingServiceUI>> = _listOfServicies
    private val _selectedService = MutableLiveData<PendingServiceUI?>()
    val selectedServiceUI: LiveData<PendingServiceUI?> = _selectedService
    var currentPosition by mutableStateOf<LatLng?>(null)

    // Distancia acumulada en metros
    var totalDistanceMetros by mutableFloatStateOf(0f)
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
                val response = getListOfService.getListOfServicePending(user)
                _listOfServicies.value = response
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
    fun deleteService(uid: String) {
        viewModelScope.launch {
            deleteServicePending.deleteServicePending(uid)
        }
    }
    fun updateLocation(newLocation: Location) {
        val newLatLng = LatLng(newLocation.latitude, newLocation.longitude)

        // 1. Calcular distancia si hay una ubicación previa
        lastLocation?.let {
            val distance = it.distanceTo(newLocation) // Devuelve metros
            if (distance > 2.0) { // Filtro de ruido (solo si se movió más de 2 metros)
                totalDistanceMetros += distance
            }
        }

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
        return String.format("%.2f",(totalDistanceMetros/1000))
    }
}