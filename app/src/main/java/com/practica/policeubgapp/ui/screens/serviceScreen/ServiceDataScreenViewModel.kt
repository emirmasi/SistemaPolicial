package com.practica.policeubgapp.ui.screens.serviceScreen

import android.util.Log.e
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.policeubgapp.domain.models.CompletedServiceUI
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import com.practica.policeubgapp.domain.usecases.GetServiceData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceDataScreenViewModel @Inject constructor(
    private val getCurrentUser: GetCurrentUser,
    private val getServiceData: GetServiceData,
) : ViewModel() {

    private val _listOfServiceComplete  = MutableStateFlow<List<CompletedServiceUI>>(emptyList())
    val listOfServiceComplete: StateFlow<List<CompletedServiceUI>> = _listOfServiceComplete

    init {
        getServicesCompleted()
    }
    fun getServicesCompleted(){
        viewModelScope.launch {
            val userEmail = getCurrentUser.getCurrentUser()?.email
            if (userEmail!= null){
                val response = getServiceData.getListServiceData(userEmail.substringBefore("@"))
                _listOfServiceComplete.value = response
            }else{
                _listOfServiceComplete.value = emptyList()
                e("no se pudo traer los servicios pendientes", "error")
            }

        }
    }
}