package com.anshyeon.imagesearchapp.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anshyeon.imagesearchapp.data.dataSource.NetworkDataSource
import com.anshyeon.imagesearchapp.data.model.UnsplashImage

class UnsplashPagingSource(
    private val networkDataSource: NetworkDataSource,
    private val query: String,
    private val onError: () -> Unit
) : PagingSource<Int, UnsplashImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        return try {
            val page = params.key ?: 1
            val response = networkDataSource.searchImages(query, page, params.loadSize)
            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.results.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            onError()
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}

