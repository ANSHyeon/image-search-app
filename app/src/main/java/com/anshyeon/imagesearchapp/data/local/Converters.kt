package com.anshyeon.imagesearchapp.data.local

import androidx.room.TypeConverter
import com.anshyeon.imagesearchapp.data.model.UnsplashImageUrl
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {

    @TypeConverter
    fun fromUnsplashPhotoUrls(urls: UnsplashImageUrl): String {
        return Json.encodeToString(urls)
    }

    @TypeConverter
    fun toUnsplashPhotoUrls(urlsString: String): UnsplashImageUrl {
        return Json.decodeFromString(urlsString)
    }
}