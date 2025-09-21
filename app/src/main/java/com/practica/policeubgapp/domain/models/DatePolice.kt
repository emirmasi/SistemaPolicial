package com.practica.policeubgapp.domain.models

class DatePolice(
    private val lp: Int,
    private val apellido: String,
    private val nombre: String,
    private val jerarquia: JERARQUIA,
    private val dependecia: String,
    private val comuna: COMUNA,
    private val foto: String,
) {
    fun getApellido(): String {
        return apellido
    }
    fun getNombre(): String {
        return nombre
    }
    fun getLp(): Int {
        return lp
    }
    fun getJerarquia(): JERARQUIA {
        return jerarquia
    }
    fun getDependencia(): String {
        return dependecia
    }
    fun getComuna(): COMUNA {
        return comuna

    }
    fun getFoto(): String {
        return foto
    }
}