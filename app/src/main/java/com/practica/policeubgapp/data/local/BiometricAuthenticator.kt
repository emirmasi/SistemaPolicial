package com.practica.policeubgapp.data.local

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt

class BiometricAuthenticator(
    private val context: Context,
) {
    fun canAuthenticate(biometricStrong: Int): Boolean {
        val manager = BiometricManager.from(context)
        return manager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
    }

    fun getPromptInfo(): BiometricPrompt.PromptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Autenticación Biométrica")
        .setSubtitle("Valide su identidad para ingresar al sistema")
        .setNegativeButtonText("Usar otro método")
        .build()
}