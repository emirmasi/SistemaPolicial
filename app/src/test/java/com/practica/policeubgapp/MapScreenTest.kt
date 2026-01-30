package com.practica.policeubgapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.firebase.firestore.GeoPoint
import com.practica.policeubgapp.domain.models.Comisaria
import com.practica.policeubgapp.domain.models.Hospital
import com.practica.policeubgapp.domain.usecases.GetComisarias
import com.practica.policeubgapp.domain.usecases.GetHospitales
import com.practica.policeubgapp.ui.screens.mapScreen.MapScreenViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.Dispatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class MapScreenTest{

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var  viewModel : MapScreenViewModel

    ///mockeamos los servicios de MapScreenViewModel
    private var getComisarias = mock<GetComisarias>()
    private var getHospitales = mock<GetHospitales>()

    @Before
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        runTest {
            whenever(getHospitales.getHospitales()).thenReturn(emptyList())
            whenever(getComisarias.getComisiarias()).thenReturn(emptyList())
        }
        viewModel = MapScreenViewModel(getComisarias, getHospitales)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `cuando loadAll es exitoso, los flujos deben tener los datos cargados`() = runTest {
        // GIVEN
        val listaHospitales = listOf(
            Hospital(
                name = "H1",
                address = "Hospital Ramos Mejía",
                commune = 3,
                specialty = "",
                type = ""
            )
        )
        val listaComisarias = listOf(
            Comisaria(
                id = 0,
                name = "Comisaría 3A",
                address = "Av. San Martín 123",
                neighborhood = "",
                commune = 3,
                phone = "",
                location = GeoPoint(0.1,0.2),
                type = ""
            )
        )

        whenever(getHospitales.getHospitales()).thenReturn(listaHospitales)
        whenever(getComisarias.getComisiarias()).thenReturn(listaComisarias)

        // WHEN
        viewModel = MapScreenViewModel(getComisarias, getHospitales)
        // El loadAll() se ejecuta en el init

        // THEN
        assertEquals(listaHospitales, viewModel.hospitales.value)
        assertEquals(listaComisarias, viewModel.comisarias.value)
    }

    @Test
    fun `seleccionarComuna debe filtrar correctamente los hospitales y comisarias por comuna`() = runTest {
        // GIVEN
        val listaHospitales = listOf(
            Hospital(
                name = "H1",
                address = "Hospital Comuna 3",
                commune = 3,
                specialty = "",
                type = ""
            ),
            Hospital(
                name = "H2",
                address = "Hospital comuna 2",
                commune = 1,
                specialty = "",
                type = ""
            )
        )
        val listaComisarias = listOf(
            Comisaria(
                id = 0,
                name = "Comisaría 3A",
                address = "Av. San Martín 123",
                neighborhood = "",
                commune = 3,
                phone = "",
                location = GeoPoint(0.1,0.2),
                type = ""
            ),
            Comisaria(
                id = 0,
                name = "Comisaría 1B",
                address = "Av. San Martín 123",
                neighborhood = "",
                commune = 1,
                phone = "",
                location = GeoPoint(0.1,0.2),
                type = ""
            )
        )

        whenever(getHospitales.getHospitales()).thenReturn(listaHospitales)
        whenever(getComisarias.getComisiarias()).thenReturn(listaComisarias)

        viewModel = MapScreenViewModel(getComisarias, getHospitales)

        // WHEN
        viewModel.seleccionarComuna(3, listOf("Balvanera", "San Cristóbal"))

        // THEN
        val resultado = viewModel.comunaSeleccionada.value
        assertEquals("Comuna 3", resultado?.comuna)
        assertEquals(1, resultado?.comisarias?.size)
        assertEquals(1, resultado?.hospitales?.size)
        assertEquals("Comisaría 3A", resultado?.comisarias?.first()?.name)
    }

    @Test
    fun `cuando ocurre un error en la carga, la lista de comisarias debe estar vacia`() = runTest {
        // GIVEN
        whenever(getHospitales.getHospitales()).thenReturn(emptyList())
        whenever(getComisarias.getComisiarias()).thenThrow(RuntimeException("Error de red"))

        // WHEN
        viewModel = MapScreenViewModel(getComisarias, getHospitales)

        // THEN
        assertEquals(0, viewModel.comisarias.value.size)
    }




}