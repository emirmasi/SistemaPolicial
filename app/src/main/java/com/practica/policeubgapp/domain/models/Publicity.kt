package com.practica.policeubgapp.domain.models

import com.google.firebase.firestore.DocumentId

data class Publicity(
    @DocumentId
    val id: String = "",
    val active: Boolean = true,
    val imagen: String = "",
    val link: String = ""
)