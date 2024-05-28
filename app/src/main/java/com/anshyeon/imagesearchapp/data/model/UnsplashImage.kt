package com.anshyeon.imagesearchapp.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashImage(
    val id: String,
    val urls: UnsplashImageUrl,
    var isLiked: Boolean = false
)

@Serializable
data class UnsplashImageUrl(
    val small: String
)
