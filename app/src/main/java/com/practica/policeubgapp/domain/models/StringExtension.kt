package com.practica.policeubgapp.domain.models

fun String.capitalizeFirst(): String{
    return this.lowercase().replaceFirstChar{
        if(it.isLowerCase()) it.titlecase() else it.toString()
    }
}