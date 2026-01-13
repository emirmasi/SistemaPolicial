package com.practica.policeubgapp.ui.screens.profileScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.policeubgapp.domain.models.PoliceDateUI
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import com.practica.policeubgapp.domain.usecases.GetPoliceDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUser: GetCurrentUser,
    private val getPoliceDate: GetPoliceDate
): ViewModel() {
    ///con el usuario vamos a traer a los datos del usuario y mostrar
    private val _dataUser: MutableStateFlow<PoliceDateUI?> = MutableStateFlow(null)
    val dataUser: StateFlow<PoliceDateUI?> = _dataUser

    init {
        getUserData()
    }
   fun getUserData(){
      viewModelScope.launch {
          try {
              val user = getCurrentUser.getCurrentUser()?.email
              val data = getPoliceDate.getPoliceDate(user?.substringBefore("@") ?: "0")
              _dataUser.value = data
          }catch (e: Exception){
              Log.e("error", e.message.toString())
          }
       }
   }

}
