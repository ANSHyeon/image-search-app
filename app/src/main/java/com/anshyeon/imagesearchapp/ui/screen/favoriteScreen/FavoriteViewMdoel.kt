package com.anshyeon.imagesearchapp.ui.screen.favoriteScreen

import androidx.lifecycle.viewModelScope
import com.anshyeon.imagesearchapp.data.model.UnsplashImage
import com.anshyeon.imagesearchapp.data.repository.ImageRepository
import com.anshyeon.imagesearchapp.ui.screen.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : BaseViewModel(imageRepository) {

    val favoriteUiState: StateFlow<FavoriteUiState> = imageRepository.getFavoriteImages().map {
        FavoriteUiState.Success(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FavoriteUiState.Loading
    )
}

sealed interface FavoriteUiState {
    data object Loading : FavoriteUiState
    data class Success(val favoriteImages: List<UnsplashImage>) : FavoriteUiState
}
