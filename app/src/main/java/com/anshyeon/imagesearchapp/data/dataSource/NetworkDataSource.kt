package com.anshyeon.imagesearchapp.data.dataSource

import com.anshyeon.imagesearchapp.data.model.UnsplashSearchResponse
import com.anshyeon.imagesearchapp.data.service.UnsplashService
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val unsplashApiService: UnsplashService
) {

    suspend fun searchImages(query: String, page: Int, perPage: Int): UnsplashSearchResponse {
        return unsplashApiService.searchImages(query, page, perPage)
    }
}