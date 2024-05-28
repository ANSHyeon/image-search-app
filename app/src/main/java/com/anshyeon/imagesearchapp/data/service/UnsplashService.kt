package com.anshyeon.imagesearchapp.data.service

import com.anshyeon.imagesearchapp.BuildConfig
import com.anshyeon.imagesearchapp.data.model.UnsplashSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashService {

    @GET("search/photos")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("client_id") clientId: String = BuildConfig.UNSPLASH_CLIENT_ID
    ): UnsplashSearchResponse
}