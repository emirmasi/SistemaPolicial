package com.practica.policeubgapp.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.practica.policeubgapp.domain.models.Publicity
import kotlinx.coroutines.delay

///a este componente solo deberiamos pasarle la lista con las publicidades
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarrouselComponent(
    list: List<Publicity>
) {
    val uriLander = LocalUriHandler.current
    // Usamos PagerState para controlar el scroll automático
    val pagerState = rememberPagerState(pageCount = { list.size })

    // Efecto para el cambio automático de imágenes
    LaunchedEffect(pagerState.currentPage) {
        delay(4000) // Tiempo de espera (4 segundos)
        var nextPage = pagerState.currentPage + 1
        if (nextPage >= list.size) nextPage = 0 // Reiniciar al llegar al final

        if (list.isNotEmpty()) {
            pagerState.animateScrollToPage(nextPage)
        }
    }

    // HorizontalPager permite que cada item ocupe todo el ancho (Full Width)
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(vertical = 16.dp),
        contentPadding = PaddingValues(horizontal = 0.dp), // Sin padding para que sea full width
        pageSpacing = 0.dp
    ) { index ->
        val item = list[index]

        AsyncImage(
            model = item.imagen,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth() // Ocupa todo el ancho disponible del Pager
                .fillMaxHeight()
                .clickable {
                    if (item.link.isNotEmpty()) {
                        try {
                            uriLander.openUri(item.link)
                        } catch (e: Exception) {
                            Log.e("Carrousel", "Error al abrir el enlace: ${e.message}")
                        }
                    }
                },
            contentScale = ContentScale.Crop
        )
    }
}