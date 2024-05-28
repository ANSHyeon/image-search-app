package com.anshyeon.imagesearchapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anshyeon.imagesearchapp.data.local.dao.ImageDao
import com.anshyeon.imagesearchapp.data.model.UnsplashImage

@Database(entities = [UnsplashImage::class], version = 1)
@TypeConverters(Converters::class)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}