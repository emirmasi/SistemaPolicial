package com.practica.policeubgapp.ui.screens.profileScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.practica.policeubgapp.domain.models.PoliceDate
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getCurrentUser: GetCurrentUser
): ViewModel() {
    ///con el usuario vamos a traer a los datos del usuario y mostrar
    private val _dataUser : MutableStateFlow<PoliceDate?> = MutableStateFlow<PoliceDate?>(null)
    val dataUser: StateFlow<PoliceDate?> = _dataUser

   fun getUserData(){
      viewModelScope.launch {
          try {
              val user = getCurrentUser.getCurrentUser()

          }catch (e: Exception){
              Log.e("error", e.message.toString())
          }

       }
   }
    ///con el lp podemos traer ya los datos del policia
    fun getPoliceDate(lp: String){
        viewModelScope.launch {

        }
    }




}
