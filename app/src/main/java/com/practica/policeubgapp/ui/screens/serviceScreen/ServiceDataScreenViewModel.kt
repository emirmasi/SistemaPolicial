package com.practica.policeubgapp.ui.screens.serviceScreen

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.policeubgapp.domain.models.CompletedServiceUI
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import com.practica.policeubgapp.domain.usecases.GetServiceData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ServiceDataScreenViewModel @Inject constructor(
    private val getCurrentUser: GetCurrentUser,
    private val getServiceData: GetServiceData,
) : ViewModel() {

    //mas adelante agregar filtros para una mejor busqueda
    private val _listOfServiceComplete  = MutableStateFlow<List<CompletedServiceUI>>(emptyList())
    val listOfServiceComplete: StateFlow<List<CompletedServiceUI>> = _listOfServiceComplete


    init {
        getServicesCompleted()
    }
    val sortedServices = _listOfServiceComplete
        .map { list ->
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            list.sortedByDescending { formatter.parse(it.date) }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    fun getServicesCompleted(){
        viewModelScope.launch {
            val userEmail = getCurrentUser.getCurrentUser()?.email
            if (userEmail!= null){
                 getServiceData.getListServiceData(userEmail.substringBefore("@")).collect{
                     _listOfServiceComplete.value = it
                 }
            }else{
                _listOfServiceComplete.value = emptyList()
                e("no se pudo traer los servicios pendientes", "error")
            }
        }
    }
}