package com.anshyeon.imagesearchapp.data.dataSource

import com.anshyeon.imagesearchapp.data.local.dao.ImageDao
import com.anshyeon.imagesearchapp.data.model.UnsplashImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val imageDao: ImageDao
) {

    fun getAllImages(): Flow<List<UnsplashImage>> {
        return imageDao.getAllImages()
    }

    suspend fun insertImage(image: UnsplashImage) {
        imageDao.insertImage(image)
    }

    suspend fun deleteImage(image: UnsplashImage) {
        imageDao.deleteImage(image)
    }
}