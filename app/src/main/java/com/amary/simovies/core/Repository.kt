package com.amary.simovies.core

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import com.amary.simovies.base.BasePagingSource
import com.amary.simovies.core.source.remote.RemoteSource
import com.amary.simovies.domain.model.Result
import com.amary.simovies.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class Repository(
    private val remoteSource: RemoteSource
) : IRepository {

    override fun allMovie(): Flow<PagingData<Result>> =
        BasePagingSource.build { page ->
            remoteSource.allMoviePopular(page)
        }.flow.map { paging -> paging.map { it.mapToModel() } }

    override fun allTv(): Flow<PagingData<Result>> =
        BasePagingSource.build { page ->
            remoteSource.allTvPopular(page)
        }.flow.map { paging -> paging.map { it.mapToModel() } }

    override fun pagerResource(loadState: CombinedLoadStates): Flow<Resource<Unit>> = flow {
        when (val result = loadState.source.refresh) {
            is LoadState.Loading -> emit(Resource.Loading())
            is LoadState.NotLoading -> emit(Resource.Success(Unit))
            is LoadState.Error -> {
                val error = result.error.message?.split(" - ")
                val codeResponse = error?.get(0)
                val message = error?.get(1)

                when (codeResponse?.toInt()) {
                    401 -> emit(Resource.Unauthorized(message.toString()))
                    else -> emit(Resource.Failed(message.toString()))
                }
            }
        }
    }
}