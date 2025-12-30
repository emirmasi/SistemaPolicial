package com.practica.policeubgapp.ui.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.practica.policeubgapp.domain.models.Publicity

///a este componente solo deberiamos pasarle la lista con las publicidades
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarrouselComponent(
    list: List<Publicity>
){
    val uriLander = LocalUriHandler.current
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { list.count() },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 16.dp, bottom = 16.dp),
        preferredItemWidth = 186.dp,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) { i ->
        val item = list[i]
        ///aca vamos a mostrar con coil
        AsyncImage(
            model = item.imagen,
            contentDescription = null,
            modifier = Modifier
                .height(205.dp)
                .clickable {
                    if (item.link.isNotEmpty()) {
                        try {
                            uriLander.openUri(item.link)
                        } catch (e: Exception) {
                            Log.e("Error", "Error al abrir el enlace: ${e.message}")
                        }
                    }

                },
            contentScale = ContentScale.Crop
        )
    }
}