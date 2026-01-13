package com.practica.policeubgapp.data.models

import com.practica.policeubgapp.domain.models.DISTRICT
import com.practica.policeubgapp.domain.models.PoliceDateUI
import com.practica.policeubgapp.domain.models.RANK
import com.practica.policeubgapp.domain.models.STATE

fun PoliceDateModel.toUiModel(): PoliceDateUI{
    return PoliceDateUI(
        lp = this.Lp,
        lastName = this.lastName,
        firstName = this.firstName,
        rank = try {
            // Reemplazamos espacios por guiones bajos para que coincida con el Enum
            // Ej: "OFICIAL MAYOR" -> "OFICIAL_MAYOR"
            RANK.valueOf(this.rank.uppercase().replace(" ", "_"))
        } catch (e: Exception) {
            RANK.OFICIAL
        },
        department = this.department,
        district = try {
            DISTRICT.valueOf(this.district.uppercase())
        } catch (e: Exception) {
            DISTRICT.C1
        },
        state = try {
            STATE.valueOf(this.state.uppercase())
        }catch (e: Exception){
            STATE.Efectivo
        },
        photoUrl = this.photoUrl
    )
}