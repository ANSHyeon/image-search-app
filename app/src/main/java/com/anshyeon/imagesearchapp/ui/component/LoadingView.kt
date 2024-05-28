package com.anshyeon.imagesearchapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoadingView(
    isLoading: Boolean,
    boxColor: Color = Color.Transparent,
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(boxColor),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
