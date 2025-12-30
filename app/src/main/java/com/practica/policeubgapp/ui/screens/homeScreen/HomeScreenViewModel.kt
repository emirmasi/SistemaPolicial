package com.practica.policeubgapp.ui.screens.homeScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.domain.usecases.GetPublicity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val publicityUseCase: GetPublicity
) : ViewModel() {
    private val _publicity = MutableLiveData<List<Publicity>>()
    val publicity: LiveData<List<Publicity>> = _publicity

    ///cuando se incia la viewModel vamos a traer las publicidades
    init {
        getPublicity()
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