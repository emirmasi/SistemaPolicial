package com.practica.policeubgapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LpComponent(
    lp: String,
    onLpChanged: (String) -> Unit
){
    OutlinedTextField(
        value = lp,
        onValueChange = {
            if (it.all { char -> char.isDigit() }) {
                onLpChanged(it)
            }
                        },
        label = { Text("Introduzca su LP") },
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        shape = RoundedCornerShape(12.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LpComponentPreview(){
    LpComponent("678") {
        lpnew->

    }
}