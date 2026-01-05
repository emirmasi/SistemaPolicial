package com.practica.policeubgapp.ui.screens.homeScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.policeubgapp.domain.models.PendingService
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.Publicity
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
    private val getCurrentUser: GetCurrentUser
) : ViewModel() {
    private val _publicity = MutableLiveData<List<Publicity>>()
    val publicity: LiveData<List<Publicity>> = _publicity

    private val _listOfServicies = MutableLiveData<List<PendingServiceUI>>()
    val listOfServices: LiveData<List<PendingServiceUI>> = _listOfServicies


    ///cuando se incia la viewModel vamos a traer las publicidades
    init {
      loadAllData()
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
}