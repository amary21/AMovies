package com.amary.simovies.core.source.remote

import com.amary.simovies.base.BaseRemoteSource
import com.amary.simovies.core.source.remote.network.ApiResult
import com.amary.simovies.core.source.remote.network.ApiService
import com.amary.simovies.core.source.remote.response.ApiResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class RemoteSource(
    private val apiService: ApiService,
    dispatcher: CoroutineDispatcher
) : BaseRemoteSource(dispatcher) {

    suspend fun allMoviePopular(page: Int): Flow<ApiResult<ApiResponse>> =
        getResult { apiService.allMoviePopular(page = page) }

    suspend fun allTvPopular(page: Int): Flow<ApiResult<ApiResponse>> =
        getResult { apiService.allTvPopular(page = page) }
}