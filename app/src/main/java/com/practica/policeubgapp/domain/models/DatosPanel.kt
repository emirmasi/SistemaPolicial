package com.practica.policeubgapp.domain.models

data class DatosPanel(
    val comuna: String = "",
    val barrios: List<String> = emptyList(),
    val comisarias:List<Comisaria>,
    val hospitales: List<Hospital>
)
