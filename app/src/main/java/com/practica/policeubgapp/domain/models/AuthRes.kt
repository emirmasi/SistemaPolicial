package com.practica.policeubgapp.domain.models

sealed class AuthRes<out T> {
    data class Succes<T>(val data: T): AuthRes<T>()
    data class Error(val errorMessage: String): AuthRes<Nothing>()
}