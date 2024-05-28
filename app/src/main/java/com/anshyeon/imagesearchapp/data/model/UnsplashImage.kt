package com.anshyeon.imagesearchapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "images")
@Serializable
data class UnsplashImage(
    @PrimaryKey val id: String,
    val urls: UnsplashImageUrl,
    var isLiked: Boolean = false
)

@Serializable
data class UnsplashImageUrl(
    val small: String
)
