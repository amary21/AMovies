package com.amary.core.domain.repository

import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import com.amary.core.data.Resource
import com.amary.core.domain.model.Detail
import com.amary.core.domain.model.Image
import com.amary.core.domain.model.ResultData
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun allMovie(): Flow<PagingData<ResultData>>
    fun allTv(): Flow<PagingData<ResultData>>
    fun pagerResource(loadState: CombinedLoadStates) : Flow<Resource<Unit>>
    fun contentDetail(idContent: Int, idResult: Int): Flow<Resource<Detail>>
    fun imageBackdrop(idContent: Int, idResult: Int): Flow<Resource<Image>>
    fun allFavorite(): Flow<List<ResultData>>
    fun isFavorite(idResult: Int): Flow<Boolean>
    fun setFavorite(resultData: ResultData, idContent: Int): Flow<Boolean>
}