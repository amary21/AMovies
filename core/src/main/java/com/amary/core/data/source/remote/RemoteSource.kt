package com.amary.core.data.source.remote

import com.amary.core.base.BaseRemoteSource
import com.amary.core.data.source.remote.network.ApiResult
import com.amary.core.data.source.remote.network.ApiService
import com.amary.core.data.source.remote.response.DetailResponse
import com.amary.core.data.source.remote.response.ImageResponse
import com.amary.core.data.source.remote.response.ListResponse
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