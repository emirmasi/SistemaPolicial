package com.practica.policeubgapp.ui.screens.splashScreen

import android.util.Log
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.practica.policeubgapp.R
import com.practica.policeubgapp.data.local.BiometricAuthenticator
import com.practica.policeubgapp.service.location.LocationService
import com.practica.policeubgapp.ui.navigations.NavigationRoutes
import com.practica.policeubgapp.ui.navigations.isServiceRunning

@Composable
fun SplashScreen(navHostController: NavHostController) {
    val context = LocalContext.current
    val activity = context as? FragmentActivity
    val splashVm: SplashViewModel = hiltViewModel()
    val isUserAuth by splashVm.isUserAuthenticated.collectAsState()

    val executor = remember { ContextCompat.getMainExecutor(context) }
    val biometricPrompt = remember {
        activity?.let{
            BiometricPrompt(it, executor, object : BiometricPrompt.AuthenticationCallback(){
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    Log.d("SPLASH", "Autenticado con exito")

                    if(isServiceRunning(context, LocationService::class.java)){
                        Log.d("SPLASH", "Ya esta corriendo el servicio")
                    }else{
                        Log.d("SPLASH", "No esta corriendo el servicio")
                    }
                    navHostController.navigate(NavigationRoutes.MainScreen.route){
                        popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
                    }
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    Log.d("SPLASH", "Error de autenticacion")
                    navHostController.navigate(NavigationRoutes.Login.route){
                        popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
                    }
                }
            })
        }
    }
    LaunchedEffect(isUserAuth) {
        Log.d("SPLASH", "Estado de Auth: $isUserAuth")
        if (isUserAuth == null) return@LaunchedEffect

        if (isUserAuth == true) {
            Log.d("SPLASH", "Autenticado, pidiendo huella...")

            val biometricManager = BiometricManager.from(context)
            val canAuthenticate = biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS && biometricPrompt  != null) {
                val promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Autenticación Biométrica")
                    .setSubtitle("Valide su identidad para ingresar al sistema")
                    .setNegativeButtonText("Usar contraseña")
                    .build()
                biometricPrompt.authenticate(promptInfo)
            } else {
                navHostController.navigate(route = NavigationRoutes.MainScreen.route) {
                    popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
                }
            }
        }else {
            Log.d("Splash","No hay sesion activa")
            navHostController.navigate(route = NavigationRoutes.Login.route){
                popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
            }
        }


    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.ciudad_logo1), contentDescription = null)
    }
}