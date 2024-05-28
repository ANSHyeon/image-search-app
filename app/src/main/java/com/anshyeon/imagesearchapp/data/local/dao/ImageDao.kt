package com.anshyeon.imagesearchapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anshyeon.imagesearchapp.data.model.UnsplashImage
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Query("SELECT * FROM images")
    fun getAllImages(): Flow<List<UnsplashImage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: UnsplashImage)

    @Delete
    suspend fun deleteImage(image: UnsplashImage)
}