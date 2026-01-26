package com.practica.policeubgapp.ui.screens.splashScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCurrentUser: GetCurrentUser
) : ViewModel(){

    private val _isUserAuthenticate = MutableStateFlow<Boolean?>(null)
    val isUserAuthenticated: StateFlow<Boolean?> = _isUserAuthenticate

    // cuando iniciamos la activity tiene que traer el user
    init {

        checkUserAuthenticated()
    }

     fun checkUserAuthenticated() {
         viewModelScope.launch {
             val currentUser = getCurrentUser.getCurrentUser()
             currentUser?.reload()?.addOnCompleteListener { task ->
                 if (task.isSuccessful) {
                     _isUserAuthenticate.value = true
                 } else {
                     _isUserAuthenticate.value = false
                 }

             } ?: run {
                 _isUserAuthenticate.value = false

             }
         }
    }
}