package com.practica.policeubgapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getCurrentUser: GetCurrentUser
) : ViewModel(){
    @Suppress("ktlint:standard:property-naming")
    private val isUserAuthenticate_ = MutableStateFlow<Boolean?>(null)
    val isUserAuthenticated: StateFlow<Boolean?> = isUserAuthenticate_

    // cuando iniciamos la activity tiene que traer el user
    init {
        viewModelScope.launch {
            checkUserAuthenticated()
        }
    }

    suspend fun checkUserAuthenticated() {
        val currentUser = getCurrentUser.getCurrentUser()
        isUserAuthenticate_.value = (currentUser != null)
    }
}