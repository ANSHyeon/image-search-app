package com.anshyeon.imagesearchapp.ui.component

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anshyeon.imagesearchapp.R
import com.anshyeon.imagesearchapp.data.model.UnsplashImage

@Composable
fun ImageBox(image: UnsplashImage, onLikeClick: (UnsplashImage) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(32.dp),
                model = image.urls.small,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.ic_place_holder)
            )
            IconButton(
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = { onLikeClick(image) }) {
                Icon(
                    imageVector = if (image.isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null
                )
            }
        }
    }
}