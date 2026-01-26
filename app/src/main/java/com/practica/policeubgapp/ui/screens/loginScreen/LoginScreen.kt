package com.practica.policeubgapp.ui.screens.loginScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.practica.policeubgapp.R
import com.practica.policeubgapp.ui.components.LpComponent
import com.practica.policeubgapp.ui.components.PasswordTextField
import com.practica.policeubgapp.ui.navigations.NavigationRoutes

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginVm: LoginScreenViewModel = hiltViewModel()
) {
    var lp by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    val loginState by loginVm.loginUiState

    LaunchedEffect(loginState) {
        when(loginState){
            is LoginUiState.Success -> {
                navController.navigate(NavigationRoutes.MainScreen.route){
                    popUpTo(NavigationRoutes.Login.route) { inclusive = true }
                }
            }
            is LoginUiState.Error -> {
                Toast.makeText(context, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
            else->{

            }

        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ciudad_logo1),
            contentDescription = "Logo",
            modifier = Modifier.size(250.dp)
        )
        LpComponent(
            lp = lp,
        ){lpIntroduce->
            lp = lpIntroduce
            loginVm.setLp(lpIntroduce)
        }
        PasswordTextField(
            password = password,
        ){passwordIntroduce->
            password = passwordIntroduce
            loginVm.setPassword(password)
        }
        TextButton(
            onClick = { /* Lógica de recuperación */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("¿Olvidó su contraseña?", color = MaterialTheme.colorScheme.secondary)
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                loginVm.signInWithLpAndPassword()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            modifier = Modifier
                .padding(top = 16.dp)
                .size(width = 280.dp, height = 50.dp),
            enabled = lp.isNotEmpty() && password.isNotEmpty(),
            shape = RoundedCornerShape(12.dp)
        ) {
            if(loginState is LoginUiState.Loading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            }else{
                Text("Ingresar")
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}