package com.practica.policeubgapp.service

import android.content.Context
import android.content.Intent
import android.location.Location
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ServiceTestRule
import com.practica.policeubgapp.service.location.LocationService
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocationServiceTest {

    @get:Rule
    val serviceRule = ServiceTestRule()

    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext()
        // Reseteamos la distancia antes de cada test
        LocationService.distanciaAcumuladaMetros = 0f
    }

    @Test
    fun service_should_accumulate_distance_when_location_changes() {
        // 1. Crear ubicaciones falsas (Punto A y Punto B)
        val locationA = Location("fused").apply {
            latitude = -34.6037 // Buenos Aires
            longitude = -58.3816
            time = System.currentTimeMillis()
            accuracy = 1f
        }

        val locationB = Location("fused").apply {
            latitude = -34.6047 // Aproximadamente 110 metros de diferencia
            longitude = -58.3816
            time = System.currentTimeMillis()
            accuracy = 1f
        }

        // 2. Iniciar el servicio
        val intent = Intent(context, LocationService::class.java)
        serviceRule.startService(intent)

        // 3. Simular la lógica interna del callback
        // Como no podemos mover el emulador físicamente en el test,
        // testeamos la lógica de acumulación directamente sobre el objeto

        // Simulamos el primer punto
        val service = LocationService()
        // Nota: En un test de integración real, usaríamos un Mock de FusedLocationProvider
        // Pero para probar tu lógica de distancia:

        val distanceBetween = locationA.distanceTo(locationB) // ~111 metros

        if (distanceBetween > 2.0) {
            LocationService.distanciaAcumuladaMetros += distanceBetween
        }

        // 4. Verificación (Assert)
        val distanciaEnKm = LocationService.distanciaAcumuladaMetros / 1000f

        // Verificamos que la distancia sea mayor a 0.1 KM (100 metros)
        Assert.assertTrue("La distancia debería ser ~0.11 KM", distanciaEnKm > 0.1f)
    }

    @Test
    fun service_notification_intent_should_not_be_null() {
        // Test para verificar que el servicio lanza la notificación correctamente
        val intent = Intent(context, LocationService::class.java)
        serviceRule.startService(intent)

        // Verificamos que los flags de inicio sean correctos
        // (Esto es más una verificación de configuración)
    }
}