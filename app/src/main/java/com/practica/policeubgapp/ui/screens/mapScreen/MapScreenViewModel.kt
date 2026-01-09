package com.practica.policeubgapp.ui.screens.mapScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.DatosPanel
import com.practica.policeubgapp.domain.models.Hospital
import com.practica.policeubgapp.domain.usecases.GetComisarias
import com.practica.policeubgapp.domain.usecases.GetHospitales
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapScreenViewModel @Inject constructor(
    private val getComisarias: GetComisarias,
    private val getHospitales: GetHospitales
) : ViewModel() {

    private val _comisarias = MutableStateFlow<List<Comisaria>>(emptyList())
    val comisarias: StateFlow<List<Comisaria>> = _comisarias

    private val _hospitales = MutableStateFlow<List<Hospital>>(emptyList())
    val hospitales: StateFlow<List<Hospital>> = _hospitales

    private val _comunaSeleccionada = MutableStateFlow<DatosPanel?>(null)
    val comunaSeleccionada: StateFlow<DatosPanel?> = _comunaSeleccionada

    val cabaCenter = LatLng(-34.6037, -58.3816)

    init {
        loadAll()
    }
    fun loadAll(){
        viewModelScope.launch {
            try {
                val hospitales = getHospitales.getHospitales()
                _hospitales.value = hospitales

                val comisarias =  getComisarias.getComisiarias()
                _comisarias.value = comisarias
            }catch (e:Exception){
                Log.e("no se pudo traer las comisarias", e.message.toString())
                _comisarias.value = emptyList()
            }
        }
    }

    fun seleccionarComuna(comuna: Int, listBarrios: List<String>){
        val comisariasFiltradas = _comisarias.value.filter { it.commune == comuna }
        val hospitalesFiltrados = _hospitales.value.filter { it.commune == comuna }

        _comunaSeleccionada.value = DatosPanel(
            comuna = "Comuna $comuna",
            barrios = listBarrios,///esto tengo que solucionarlo
            comisarias = comisariasFiltradas,
            hospitales = hospitalesFiltrados
        )
    }

}