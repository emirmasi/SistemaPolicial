package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.domain.models.Comisaria

interface GetComisarias {
    suspend fun getComisiarias(): List<Comisaria>
}