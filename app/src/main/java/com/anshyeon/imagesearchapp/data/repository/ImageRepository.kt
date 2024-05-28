package com.anshyeon.imagesearchapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.anshyeon.imagesearchapp.data.model.UnsplashImage
import com.anshyeon.imagesearchapp.data.UnsplashPagingSource
import com.anshyeon.imagesearchapp.data.dataSource.LocalDataSource
import com.anshyeon.imagesearchapp.data.dataSource.NetworkDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource,
) {

    fun searchImages(query: String, onError: () -> Unit): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { UnsplashPagingSource(networkDataSource, query, onError) }
        ).flow
    }

    fun getFavoriteImages(): Flow<List<UnsplashImage>> {
        return localDataSource.getAllImages()
    }

    suspend fun addImageToFavorites(image: UnsplashImage) {
        localDataSource.insertImage(image.copy(isLiked = true))
    }

    suspend fun deleteImageFromFavorites(image: UnsplashImage) {
        localDataSource.deleteImage(image)
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 30
    }
}