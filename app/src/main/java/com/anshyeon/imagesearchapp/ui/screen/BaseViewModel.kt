package com.anshyeon.imagesearchapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshyeon.imagesearchapp.data.model.UnsplashImage
import com.anshyeon.imagesearchapp.data.repository.ImageRepository
import com.anshyeon.imagesearchapp.utilities.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseViewModel(
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _snackBarText = MutableStateFlow("")
    val snackBarText: StateFlow<String> = _snackBarText

    private val _showSnackBar = MutableStateFlow(false)
    val showSnackBar: StateFlow<Boolean> = _showSnackBar

    private var lastRemovedImage: UnsplashImage? = null

    fun toggleFavorite(image: UnsplashImage) {
        viewModelScope.launch {
            if (image.isLiked) {
                imageRepository.deleteImageFromFavorites(image)
                lastRemovedImage = image
                showSnackBarWithMessage(Constants.REMOVE_IMAGE_MESSAGE)
            } else {
                imageRepository.addImageToFavorites(image)
            }
        }
    }

    fun undoRemovedImage() {
        viewModelScope.launch {
            lastRemovedImage?.let { image ->
                imageRepository.addImageToFavorites(image)
            }
        }
    }

    protected fun showSnackBarWithMessage(message: String) {
        _snackBarText.value = message
        _showSnackBar.value = true
    }

    fun dismissSnackBar() {
        _showSnackBar.value = false
    }
}