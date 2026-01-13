package com.practica.policeubgapp.ui.screens.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import com.practica.policeubgapp.domain.usecases.SignOut
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val signOut: SignOut,
    private val getCurrentUser: GetCurrentUser,
): ViewModel(){
    ///en esta viewModel vamos a desloguearnos y vamos a traer el usuario para mostrar los datos de  la db
    //private val currentUser: PoliceDate = TODO()


    ///funcion para desloguearnos
    fun signOut(){
        viewModelScope.launch {
            signOut.signOut()
        }
    }
}