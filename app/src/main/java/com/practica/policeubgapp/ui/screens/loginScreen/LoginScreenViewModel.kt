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
    object Idle: LoginUiState
    object Success : LoginUiState

    object Loading :LoginUiState

    data class Error(val message: String) : LoginUiState
}

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val signInWithLPAndPassword: SignInWithLpAndPassword
): ViewModel(){
    private val _loginUiState = mutableStateOf<LoginUiState>(
        LoginUiState.Idle)
    val loginUiState: State<LoginUiState> = _loginUiState

    private val _lp = mutableStateOf("")
    val lp: State<String> = _lp

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    fun setLp(value: String) {
        _lp.value = value
    }
    fun convertLp(lp: String): String{
        return "$lp@gmail.com"
    }
    fun setPassword(value: String) {
        _password.value = value
    }

    fun signInWithLpAndPassword(){
        if(_lp.value.isEmpty() || _password.value.isEmpty()){
            _loginUiState.value = LoginUiState.Error("Los campos no pueden estar vacios")
            return
        }
        _loginUiState.value = LoginUiState.Loading
        viewModelScope.launch {
            try {
                signInWithLPAndPassword.signInWithLpAndPassword(lp = convertLp(_lp.value),password = _password.value)
                _loginUiState.value = LoginUiState.Success
            }catch (e: Exception){
                Log.e("sigIn", "failed sign in with email anda password")
                _loginUiState.value = LoginUiState.Error("LP o contraseña incorrecto")
            }
        }
    }
    fun resetPassword() {
        if (_lp.value.isEmpty()) {
            _loginUiState.value = LoginUiState.Error("Ingrese su LP para recuperar")
            return
        }

        viewModelScope.launch {
            try {
                val email = convertLp(_lp.value)
                //Firebase.auth.sendPasswordResetEmail(email).await()
                // Podrías crear un nuevo estado SuccessReset para mostrar un mensaje
            } catch (e: Exception) {
                _loginUiState.value = LoginUiState.Error("Error al enviar correo")
            }
        }
    }
}