package com.practica.policeubgapp


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.firebase.firestore.GeoPoint
import com.practica.policeubgapp.domain.models.PendingServiceUI
import com.practica.policeubgapp.domain.models.Publicity
import com.practica.policeubgapp.domain.models.SCHEDULE
import com.practica.policeubgapp.domain.models.TYPESERVICE
import com.practica.policeubgapp.domain.usecases.DeleteServicePending
import com.practica.policeubgapp.domain.usecases.GetCurrentUser
import com.practica.policeubgapp.domain.usecases.GetListOfServicePending
import com.practica.policeubgapp.domain.usecases.GetPublicity
import com.practica.policeubgapp.domain.usecases.UploadCompleteService
import com.practica.policeubgapp.ui.screens.homeScreen.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever


@OptIn(ExperimentalCoroutinesApi::class)
class HomeScreenViewModelTest {

    // Regla para ejecutar LiveData de forma instantánea
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    // 1. Definimos el dispatcher de prueba
    private val testDispatcher = UnconfinedTestDispatcher()

    // Mocks de todos los casos de uso que recibe el constructor
    private val publicityUseCase = mock<GetPublicity>()
    private val getListOfService = mock<GetListOfServicePending>()
    private val getCurrentUser = mock<GetCurrentUser>()
    private val deleteServicePending = mock<DeleteServicePending>()
    private val uploadServiceComplete = mock<UploadCompleteService>()

    private lateinit var viewModel: HomeScreenViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher) // Redirige el Main dispatcher para tests
        // Configuración por defecto para que el init no falle
        CoroutineScope(testDispatcher).launch {
            whenever(getListOfService.getListOfServicePending(mock())).thenReturn(MutableStateFlow(emptyList()))
            whenever(publicityUseCase.getPublicity()).thenReturn(emptyList())
            whenever(getCurrentUser.getCurrentUser()).thenReturn(null)
        }
        viewModel = HomeScreenViewModel(
            publicityUseCase,
            getListOfService,
            getCurrentUser,
            deleteServicePending,
            uploadServiceComplete
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `cuando loadAllData es llamado, se deben cargar las publicidades correctamente`() = runTest {
        // GIVEN
        val expectedPublicity = listOf(Publicity("Promo Policial",active = true ,imagen = "", link = ""))
        whenever(publicityUseCase.getPublicity()).thenReturn(expectedPublicity)

        // WHEN
        viewModel.loadAllData()

        // THEN
        assertEquals(expectedPublicity, viewModel.publicity.value)
    }
    //este falla porque devuelve un vacio
    @Test
    fun `cuando loadAllData es llamado, se deben cargar los servicios pendientes correctamente`() = runTest {
        // GIVEN
        val expectedService = MutableStateFlow(listOf(
            PendingServiceUI(
                uid = "123",
                lp = 12345,
                typeService = TYPESERVICE.UBG,
                locationName = "Test Location",
                location = GeoPoint(1.0, 2.0),
                schedule = SCHEDULE.MAÑANA,
                date = "10/10/2023"
            )
        ))
        whenever(getListOfService.getListOfServicePending("6252@gmail.com")).thenReturn(expectedService)
        //when
        viewModel.loadAllData()

        // THEN
        assertEquals(expectedService.value, viewModel.listOfServices.value)
    }

    @Test
    fun `timestampeToHours debe calcular correctamente la diferencia de tiempo en decimal`() {
        // GIVEN: 2 horas y 30 minutos de diferencia
        val start = mock<com.google.firebase.Timestamp>()
        val end = mock<com.google.firebase.Timestamp>()

        val dateStart = java.util.Date(1000000) // Tiempo arbitrario
        val dateEnd = java.util.Date(1000000 + (3600 * 2.5 * 1000).toLong()) // +2.5 horas

        whenever(start.toDate()).thenReturn(dateStart)
        whenever(end.toDate()).thenReturn(dateEnd)


        // WHEN
        val result = viewModel.timestampeToHours(start, end)

        // THEN
        assertEquals(2.5f, result)
    }
}