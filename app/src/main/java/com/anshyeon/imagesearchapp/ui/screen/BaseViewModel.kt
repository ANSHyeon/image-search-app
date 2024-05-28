package com.anshyeon.imagesearchapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anshyeon.imagesearchapp.data.model.UnsplashImage
import com.anshyeon.imagesearchapp.data.repository.ImageRepository
import kotlinx.coroutines.launch

open class BaseViewModel(
    private val imageRepository: ImageRepository
) : ViewModel() {

    fun toggleFavorite(image: UnsplashImage) {
        viewModelScope.launch {
            if (image.isLiked) {
                imageRepository.deleteImageFromFavorites(image)
            } else {
                imageRepository.addImageToFavorites(image)
            }
        }
    }
}