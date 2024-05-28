@file:OptIn(ExperimentalMaterial3Api::class)

package com.anshyeon.imagesearchapp.ui.screen.feedScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.anshyeon.imagesearchapp.R
import com.anshyeon.imagesearchapp.data.model.UnsplashImage
import com.anshyeon.imagesearchapp.ui.component.ImageBox
import com.anshyeon.imagesearchapp.ui.component.TextSnackBarContainer

@Composable
fun FeedScreen(viewModel: FeedViewModel = hiltViewModel()) {
    val query by viewModel.searchQuery.collectAsStateWithLifecycle()
    val feedUiState = viewModel.feedUiState.collectAsStateWithLifecycle().value
    val searchResults = viewModel.searchResults.collectAsLazyPagingItems()
    val snackBarTextState by viewModel.snackBarText.collectAsStateWithLifecycle()
    val showSnackBarState by viewModel.showSnackBar.collectAsStateWithLifecycle()

    Surface(Modifier.fillMaxSize()) {
        TextSnackBarContainer(
            snackbarText = snackBarTextState,
            showSnackbar = showSnackBarState,
            onDismissSnackbar = { viewModel.dismissSnackBar() }
        ) {
            FeedBody(feedUiState, query, viewModel, searchResults)
        }
    }

}

@Composable
private fun FeedBody(
    uiState: FeedUiState,
    query: String,
    viewModel: FeedViewModel,
    searchResults: LazyPagingItems<UnsplashImage>
) {
    Column {
        SearchBar(query, viewModel)
        when (uiState) {
            is FeedUiState.Start -> EmptyFeedBody()
            is FeedUiState.Success -> SearchResultBody(searchResults, viewModel)
        }
    }
}

@Composable
private fun SearchBar(
    query: String,
    viewModel: FeedViewModel
) {
    TextField(
        value = query,
        onValueChange = { viewModel.updateQuery(it) },
        placeholder = {
            Text(
                text = stringResource(id = R.string.hint_search_image)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            containerColor = Color.LightGray,
            textColor = Color.Black
        ),
        textStyle = TextStyle(
            fontSize = 16.sp,
        ),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        maxLines = 1,
        shape = RoundedCornerShape(32.dp),
    )
}

@Composable
private fun SearchResultBody(
    searchResults: LazyPagingItems<UnsplashImage>,
    viewModel: FeedViewModel
) {
    LazyColumn {
        items(searchResults.itemCount) { index ->
            searchResults[index]?.let { image ->
                ImageBox(
                    image = image,
                    onLikeClick = { viewModel.toggleFavorite(image) })
            }
        }
    }
}

@Composable
private fun EmptyFeedBody(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.start_search_message),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}