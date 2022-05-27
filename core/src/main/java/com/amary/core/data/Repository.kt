package com.amary.core.data

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import com.amary.core.base.BasePagingSource
import com.amary.core.data.source.local.LocalSource
import com.amary.core.data.source.remote.RemoteSource
import com.amary.core.data.source.remote.network.ApiResult
import com.amary.core.domain.model.Detail
import com.amary.core.domain.model.Image
import com.amary.core.domain.model.ResultData
import com.amary.core.domain.repository.IRepository
import kotlinx.coroutines.flow.*

class Repository(
    private val remoteSource: RemoteSource,
    private val localSource: LocalSource
) : IRepository {

    override fun allMovie(): Flow<PagingData<ResultData>> =
        BasePagingSource.build { page ->
            remoteSource.allMoviePopular(page)
        }.flow.map { paging -> paging.map { it.mapToModel() } }

    override fun allTv(): Flow<PagingData<ResultData>> =
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

    override fun allFavorite(): Flow<List<ResultData>> = flow {
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

    override fun setFavorite(resultData: ResultData, idContent: Int): Flow<Boolean> = flow {
        val output = isFavorite(resultData.id).first()
        if (output) {
            localSource.unSetFavorite(resultData.id)
        } else {
            localSource.setFavorite(resultData.mapToEntity(idContent))
        }
        emit(!output)
    }
}