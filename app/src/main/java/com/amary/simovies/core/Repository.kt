package com.amary.simovies.core

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import com.amary.simovies.base.BasePagingSource
import com.amary.simovies.core.source.local.LocalSource
import com.amary.simovies.core.source.remote.RemoteSource
import com.amary.simovies.core.source.remote.network.ApiResult
import com.amary.simovies.domain.model.Detail
import com.amary.simovies.domain.model.Image
import com.amary.simovies.domain.model.Result
import com.amary.simovies.domain.repository.IRepository
import kotlinx.coroutines.flow.*

class Repository(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource
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
            is LoadState.Error -> emit(Resource.Failed(result.error.message.toString()))
        }
    }

    override fun contentDetail(idContent: Int, idResult: Int): Flow<Resource<Detail>> = flow {
        emit(Resource.Loading())
        val content = if (idContent == 1) "movie" else "tv"
        when(val result = remoteSource.contentDetail(content, idResult).first()){
            is ApiResult.Success -> emit(Resource.Success(result.data.mapToModel()))
            is ApiResult.Error -> emit(Resource.Failed(result.errorMessage))
        }
    }

    override fun imageBackdrop(idContent: Int, idResult: Int): Flow<Resource<Image>> = flow {
        emit(Resource.Loading())
        val content = if (idContent == 1) "movie" else "tv"
        when(val result = remoteSource.imageBackdrop(content, idResult).first()){
            is ApiResult.Success -> emit(Resource.Success(result.data.mapToModel()))
            is ApiResult.Error -> emit(Resource.Failed(result.errorMessage))
        }
    }

    override fun allFavorite(): Flow<List<Result>> = flow {
        localSource.allFavoriteStories().collect { result ->
            emit(result.map { it.mapToModel() })
        }
    }

    override fun isFavorite(idResult: Int): Flow<Boolean> = flow {
        val result = localSource.isFavorite(idResult).first()
        if (result == 0){
            emit(false)
        } else {
            emit(true)
        }
    }

    override fun setFavorite(result: Result, idContent: Int): Flow<Boolean> = flow {
        val output = isFavorite(result.id).first()
        if (output) {
            localSource.unSetFavorite(result.id)
        } else {
            localSource.setFavorite(result.mapToEntity(idContent))
        }
        emit(!output)
    }
}