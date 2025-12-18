package com.practica.policeubgapp.ui.screens.loginScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.policeubgapp.domain.usecases.SignInWithLpAndPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface LoginUiState{
    object Success : LoginUiState

    object Loading :LoginUiState

    object Error : LoginUiState
}

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val signInWithLPAndPassword: SignInWithLpAndPassword
): ViewModel(){
    private val _loginUiState = mutableStateOf<LoginUiState>(
        LoginUiState.Success)
    val loginUiState: State<LoginUiState> = _loginUiState

    private val _lp = mutableStateOf("")
    val lp: State<String> = _lp

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    fun setLp(value: Int) {
        _lp.value = convertLp(value)
    }
    fun convertLp(value: Int): String{
        return "$value@gmail.com"
    }

    fun setPassword(value: String) {
        _password.value = value
    }

    fun signInWithLpAndPassword(){
        _loginUiState.value = LoginUiState.Loading
        viewModelScope.launch {
            try {
                signInWithLPAndPassword.signInWithLpAndPassword(lp = _lp.value,password = _password.value)
                _loginUiState.value = LoginUiState.Success
            }catch (e: Exception){
                Log.e("sigIn", "failed sign in with email anda password")
                _loginUiState.value = LoginUiState.Error
            }
        }
    }
}