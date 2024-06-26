package com.anshyeon.imagesearchapp.ui.screen.feedScreen

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.anshyeon.imagesearchapp.data.model.UnsplashImage
import com.anshyeon.imagesearchapp.data.repository.ImageRepository
import com.anshyeon.imagesearchapp.ui.screen.BaseViewModel
import com.anshyeon.imagesearchapp.utilities.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : BaseViewModel(imageRepository) {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _feedUiState: MutableStateFlow<FeedUiState> = MutableStateFlow(FeedUiState.Start)
    val feedUiState: StateFlow<FeedUiState> = _feedUiState

    private val _searchResults: MutableStateFlow<PagingData<UnsplashImage>> =
        MutableStateFlow(PagingData.empty())
    val searchResults: StateFlow<PagingData<UnsplashImage>> = _searchResults

    private val favoriteImages = imageRepository.getFavoriteImages()
        .map { favorites ->
            favorites.map { it.id }
        }

    @OptIn(FlowPreview::class)
    val debouncedSearchQuery: Flow<String> = searchQuery
        .debounce(Constants.SEARCH_DEBOUNCE_TIME)
        .filter { it.isNotEmpty() }
        .distinctUntilChanged()

    init {
        viewModelScope.launch {
            getSearchResults()
                .collect { pagingData ->
                    _searchResults.emit(pagingData)
                    setFeedUiState(FeedUiState.Success)
                }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getSearchResults(): Flow<PagingData<UnsplashImage>> {
        return debouncedSearchQuery
            .flatMapLatest { queryString ->
                imageRepository.searchImages(queryString) {
                    setFeedUiState(FeedUiState.Error)
                    showSnackBarWithMessage(Constants.RETRY_ERROR_MESSAGE)
                }.cachedIn(viewModelScope)
            }.combine(favoriteImages) { search, favorite ->
                search.map { unsplashImage ->
                    unsplashImage.copy(isLiked = favorite.any { it == unsplashImage.id })
                }
            }
    }

    fun updateQuery(newQuery: String) {
        _searchQuery.value = newQuery
    }

    private fun setFeedUiState(uiState: FeedUiState) {
        viewModelScope.launch {
            _feedUiState.emit(uiState)
        }
    }
}

sealed interface FeedUiState {
    data object Start : FeedUiState
    data object Error : FeedUiState
    data object Success : FeedUiState
}