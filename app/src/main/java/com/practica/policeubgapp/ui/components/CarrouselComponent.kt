package com.practica.policeubgapp.ui.components

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
    LaunchedEffect(Unit) {
        while (true) {
            delay(4000)
            if(list.isNotEmpty()){
                val nextPage = (pagerState.currentPage + 1) % list.size
                pagerState.animateScrollToPage(nextPage)
            }
        }

    }

    // HorizontalPager permite que cada item ocupe todo el ancho (Full Width)
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(vertical = 16.dp),
            contentPadding = PaddingValues( 0.dp), // Sin padding para que sea full width
            pageSpacing = 0.dp
        ) { index ->
            val item = list[index]

            AsyncImage(
                model = item.imagen,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize() // Ocupa todo el ancho disponible del Pager
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        if (item.link.isNotEmpty()) {
                            try {
                                uriLander.openUri(item.link)
                            } catch (e: Exception) {
                                Log.e("Carrousel", "Error al abrir el enlace: ${e.message}")
                            }
                        }
                    },
                contentScale = ContentScale.FillWidth
            )
        }
        //indicadores puntitos
        if(list.isNotEmpty()){
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(list.size){index->
                    val isSelected = pagerState.currentPage == index

                    val size by animateDpAsState(
                        targetValue = if (isSelected) 10.dp else 8.dp,
                        label = ""
                    )
                    val color by animateColorAsState(
                        targetValue = if (isSelected) {
                            Color(0xFF00ACC1)
                        } else Color.LightGray,
                        label = ""
                    )

                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(CircleShape)
                            .size(size)
                            .background(color)
                    )

                }
            }
        }
    }

}