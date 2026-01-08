package com.practica.policeubgapp.ui.screens.mapScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.DatosPanel
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

    private val _hospitales = MutableStateFlow<List<DatosPanel>>(emptyList())
    val hospitales: StateFlow<List<DatosPanel>> = _hospitales

    private val _comunaSeleccionada = MutableStateFlow<DatosPanel?>(null)
    val comunaSeleccionada: StateFlow<DatosPanel?> = _comunaSeleccionada

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

    fun seleccionarComuna(comuna: Int){
        val comisariasFiltradas = _comisarias.value.filter { it.commune == comuna }
        val hospitalesFiltrados = _hospitales.value.filter { it.comuna.toInt() == comuna }

        val datosPanel = DatosPanel(
            comuna = "Comuna $comuna",
            barrios = "100",
            comisarias = comisariasFiltradas.size.toString(),
            hospitales = hospitalesFiltrados.size.toString()
        )
    }

}