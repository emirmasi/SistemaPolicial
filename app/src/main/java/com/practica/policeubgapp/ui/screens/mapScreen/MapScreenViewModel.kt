package com.practica.policeubgapp.ui.screens.mapScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.MarkerData
import com.practica.policeubgapp.domain.usecases.GetComisarias
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val getComisarias: GetComisarias
) : ViewModel() {

    private val _comisarias = MutableStateFlow<List<Comisaria>>(emptyList())
    val comisarias: StateFlow<List<Comisaria>> = _comisarias

    private val _hospitales = MutableStateFlow<List<MarkerData>>(emptyList())
    val hospitales: StateFlow<List<MarkerData>> = _hospitales
    val cabaCenter = LatLng(-34.6037, -58.3816)

    init {
        loadAll()
    }
    fun loadAll(){
        viewModelScope.launch {
            try {
                val comisarias =  getComisarias.getComisiarias()
                _comisarias.value = comisarias
            }catch (e:Exception){
                Log.e("no se pudo traer las comisarias", e.message.toString())
                _comisarias.value = emptyList()
            }
        }
    }

}