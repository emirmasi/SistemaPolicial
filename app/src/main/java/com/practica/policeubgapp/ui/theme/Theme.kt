package com.practica.policeubgapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// --- MODO OSCURO (BÁSICO) ---
private val DarkColorScheme = darkColorScheme(
    primary = PoliciaCyan,        // Mantenemos el Cyan para botones
    onPrimary = Color.Black,
    secondary = PoliciaCyan,
    onSecondary = Color.Black,

    // Grises oscuros para el fondo y superficies en modo oscuro
    background = Color(0xFF121212),
    onBackground = PoliciaBlancoPuro,
    surface = Color(0xFF1E1E1E),
    onSurface = PoliciaBlancoPuro,

    outline = PoliciaGrisBordeInput, // Opcional: ajustar para modo oscuro
    error = PoliciaRojoError
)

// --- MODO CLARO (PALETA OFICIAL POLICÍA DE LA CIUDAD) ---
private val LightColorScheme = lightColorScheme(
    // 1. Colores Principales (Brand)
    primary = PoliciaCyan,          // Botones principales (Ingresar), Enlaces activos
    onPrimary = PoliciaBlancoPuro,   // Texto sobre botones Primary
    secondary = PoliciaNegro,       // Títulos, elementos de marca secundarios
    onSecondary = PoliciaBlancoPuro, // Texto sobre elementos Negro

    // 2. Colores Neutros de Superficie y Fondo
    background = PoliciaFondoGrisClaro, // Fondo general de la pantalla
    onBackground = PoliciaNegro,        // Texto principal sobre fondo gris

    surface = PoliciaBlancoPuro,         // Fondo de inputs, cards, containers
    onSurface = PoliciaNegro,           // Texto escrito dentro de inputs

    outline = PoliciaGrisBordeInput,    // Borde de OutlinedTextField (Gris medio)

    // 3. Colores de Estado
    tertiary = PoliciaGrisPlaceholder,  // Textos placeholder, iconos secundarios
    onTertiary = PoliciaBlancoPuro,

    error = PoliciaRojoError,           // Errores de validación
    onError = PoliciaBlancoPuro
)

@Composable
fun PoliceUbgAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
       /* dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        */
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}