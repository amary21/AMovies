package com.amary.simovies.domain.usecase

import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import com.amary.simovies.core.Resource
import com.amary.simovies.domain.model.Detail
import com.amary.simovies.domain.model.Image
import com.amary.simovies.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface UseCase {
    fun allMovie(): Flow<PagingData<Result>>
    fun allTv(): Flow<PagingData<Result>>
    fun pagerResource(loadState: CombinedLoadStates) : Flow<Resource<Unit>>
    fun contentDetail(idContent: Int, idResult: Int): Flow<Resource<Detail>>
    fun imageBackdrop(idContent: Int, idResult: Int): Flow<Resource<Image>>
    fun allFavorite(): Flow<List<Result>>
    fun isFavorite(idResult: Int): Flow<Boolean>
    fun setFavorite(result: Result, idContent: Int): Flow<Boolean>
}