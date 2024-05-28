package com.anshyeon.imagesearchapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashSearchResponse(
    val results: List<UnsplashImage>,
    @SerialName("total_pages") val totalPages: Int
)
