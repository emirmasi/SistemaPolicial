package com.practica.policeubgapp.ui.screens.loginScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Image(
            painter = painterResource(id = R.drawable.ciudad_logo1),
            contentDescription = "Logo",
            modifier = Modifier.size(250.dp)
        )
        LpComponent(
            lp = lp,
        ){lpIntroduce->
            lp = lpIntroduce
            if (loginState is LoginUiState.Error) loginVm.resetState()
            loginVm.setLp(lpIntroduce)
        }
        Spacer(modifier = Modifier.height(12.dp))
        PasswordTextField(
            password = password,
        ){passwordIntroduce->
            password = passwordIntroduce
            if (loginState is LoginUiState.Error) loginVm.resetPassword()
            loginVm.setPassword(password)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(
                onClick = { /* Lógica de recuperación */ },
                // Eliminamos padding interno para alinearlo perfectamente con el borde del input
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    "¿Olvidó su contraseña?",
                    // MEJORA: Usamos 'primary' (Cyan) para indicar que es un enlace activo
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                loginVm.signInWithLpAndPassword()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = lp.isNotEmpty() && password.isNotEmpty(),
            shape = RoundedCornerShape(12.dp)
        ) {
            if(loginState is LoginUiState.Loading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp),
                    strokeWidth = 2.5.dp
                )
            }else{
                Text(
                    "Ingresar",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

        }
        Spacer(modifier = Modifier.height(24.dp)) // Espacio entre botón y texto final

        Text(
            text = "Acceso restringido a personal de la Policía de la Ciudad de Buenos Aires",
            // MEJORA: Usamos 'tertiary' (Gris oscuro) para este texto de ayuda
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            lineHeight = 16.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}