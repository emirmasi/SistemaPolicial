package com.practica.policeubgapp.ui.screens.loginScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
) {
    var lp by remember { mutableIntStateOf(0) }
    var password by remember { mutableStateOf("") }
    val loginVm: LoginScreenViewModel = hiltViewModel()


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
        Button(
            onClick = {
                loginVm.signInWithLpAndPassword()
                navController.navigate(route = NavigationRoutes.MainScreen.route)
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

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())

}