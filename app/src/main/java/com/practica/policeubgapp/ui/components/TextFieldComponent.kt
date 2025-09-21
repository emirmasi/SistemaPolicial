package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.practica.policeubgapp.R

@Composable
fun LpComponent(
    lp: String,
    onLpChanged: (String) -> Unit
){

    OutlinedTextField(
        value = lp,
        onValueChange ={ newLp->
            if (newLp.all { it.isDigit()  }){
                onLpChanged(newLp)
            }
        } ,
        label = { Text("Introduzca su LP") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}