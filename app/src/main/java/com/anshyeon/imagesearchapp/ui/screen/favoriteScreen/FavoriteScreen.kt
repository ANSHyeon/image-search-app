package com.anshyeon.imagesearchapp.ui.screen.favoriteScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anshyeon.imagesearchapp.R
import com.anshyeon.imagesearchapp.ui.component.ImageBox
import com.anshyeon.imagesearchapp.ui.component.LoadingView
import com.anshyeon.imagesearchapp.ui.component.TextSnackBarContainer

@Composable
fun FavoriteScreen(viewModel: FavoriteViewModel = hiltViewModel()) {
    val favoriteUiState by viewModel.favoriteUiState.collectAsStateWithLifecycle()
    val snackBarTextState by viewModel.snackBarText.collectAsStateWithLifecycle()
    val showSnackBarState by viewModel.showSnackBar.collectAsStateWithLifecycle()

    Surface(Modifier.fillMaxSize()) {
        TextSnackBarContainer(
            snackbarText = snackBarTextState,
            showSnackbar = showSnackBarState,
            onDismissSnackbar = { viewModel.dismissSnackBar() },
            onAction = { viewModel.undoRemovedImage() },
            actionLael = stringResource(id = R.string.action_label_removed_image)
        ) {
            when (val state = favoriteUiState) {
                is FavoriteUiState.Loading -> LoadingView(isLoading = true)
                is FavoriteUiState.Success -> FavoriteBody(state, viewModel)
            }
        }
    }
}

@Composable
private fun FavoriteBody(
    state: FavoriteUiState.Success,
    viewModel: FavoriteViewModel
) {
    if (state.favoriteImages.isNotEmpty()) {
        LazyColumn {
            items(state.favoriteImages) { image ->
                ImageBox(
                    image = image,
                    onLikeClick = { viewModel.toggleFavorite(image) })
            }
        }
    } else {
        EmptyFavorites()
    }
}

@Composable
private fun EmptyFavorites(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.size(72.dp),
            painter = painterResource(id = R.drawable.ic_favorite_outline),
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = stringResource(id = R.string.favorites_empty_message),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}