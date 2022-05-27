package com.amary.simovies.core.source.remote

import com.amary.simovies.base.BaseRemoteSource
import com.amary.simovies.core.source.remote.network.ApiResult
import com.amary.simovies.core.source.remote.network.ApiService
import com.amary.simovies.core.source.remote.response.DetailResponse
import com.amary.simovies.core.source.remote.response.ImageResponse
import com.amary.simovies.core.source.remote.response.ListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class RemoteSource(
    private val apiService: ApiService,
    dispatcher: CoroutineDispatcher
) : BaseRemoteSource(dispatcher) {

    suspend fun allMoviePopular(page: Int): Flow<ApiResult<ListResponse>> =
        getResult { apiService.allMoviePopular(page = page) }

    suspend fun allTvPopular(page: Int): Flow<ApiResult<ListResponse>> =
        getResult { apiService.allTvPopular(page = page) }

    suspend fun contentDetail(content: String, idResult: Int): Flow<ApiResult<DetailResponse>> =
        getResult { apiService.contentDetail(content = content, idResult = idResult) }

    suspend fun imageBackdrop(content: String, idResult: Int): Flow<ApiResult<ImageResponse>> =
        getResult { apiService.imageBackdrop(content = content, idResult = idResult) }
}