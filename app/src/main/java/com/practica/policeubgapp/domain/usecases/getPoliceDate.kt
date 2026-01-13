package com.practica.policeubgapp.domain.usecases

import com.practica.policeubgapp.domain.models.PoliceDateUI

interface GetPoliceDate {
    suspend fun getPoliceDate(lp:String): PoliceDateUI
}

