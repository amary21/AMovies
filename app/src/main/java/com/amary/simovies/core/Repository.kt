package com.amary.simovies.core

import androidx.paging.PagingData
import androidx.paging.map
import com.amary.simovies.base.BasePagingSource
import com.amary.simovies.core.source.remote.RemoteSource
import com.amary.simovies.domain.model.Result
import com.amary.simovies.domain.repository.IRepository
import kotlinx.coroutines.flow.Flow
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
}