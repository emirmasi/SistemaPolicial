package com.practica.policeubgapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lint.kotlin.metadata.Visibility
import com.practica.policeubgapp.R

/*
en esta screen se muestra la pantalla incial del login,
se loguea con lp
 */
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ///logo de la policia de la ciudad
        Image(
            painter = painterResource(id = R.drawable.ciudad_logo1),
            contentDescription = "Logo",
            modifier = Modifier.size(250.dp)
        )
        PasswordTextField(
            password = password,

        ){passwordIntroduce->
            password = passwordIntroduce
            //aca lo tengo que validar con la base de datos
        }
        Button(
            onClick = {
                ///aca iria la navegación y la lógica de inicio de sesión
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .padding(top = 16.dp)
                .size(width = 280.dp, height = 50.dp)
        ) {
            Text("Ingresar")
        }

    }
}

@Composable
fun PasswordTextField(
    password: String,
    onPasswordChanged: (String) -> Unit
){
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChanged,
        label = { Text("Introduzca su LP") },
        singleLine = true,
        visualTransformation = if (passwordVisible)
            VisualTransformation.None
        else
            PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                R.drawable.baseline_visibility_24
            else
                R.drawable.baseline_visibility_off_24

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = painterResource(id = image),
                    contentDescription = if (passwordVisible)
                        "Hide password"
                    else
                        "Show password"
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}