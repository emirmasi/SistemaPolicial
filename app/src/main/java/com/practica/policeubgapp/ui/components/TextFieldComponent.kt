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
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import com.practica.policeubgapp.R

@Composable
fun LpComponent(
    lp: Int,
    onLpChanged: (Int) -> Unit
){

    OutlinedTextField(
        value = lp.toString(),
        onValueChange ={ newLpString:String->
            if (newLpString.all { it.isDigit() } || newLpString.isEmpty()) {
                // 4. Intentamos convertir el String de nuevo a Int.
                // Si es vacío, podemos pasarlo como 0 o dejarlo sin pasar el cambio.
                val newLpInt = newLpString.toIntOrNull() ?: 0

                onLpChanged(newLpInt) }
                       },
        label = { Text("Introduzca su LP") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LpComponentPreview(){
    LpComponent(678) {
        lpnew->

    }
}