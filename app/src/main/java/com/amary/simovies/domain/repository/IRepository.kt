package com.amary.simovies.domain.repository

import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import com.amary.simovies.core.Resource
import com.amary.simovies.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun allMovie(): Flow<PagingData<Result>>
    fun allTv(): Flow<PagingData<Result>>
    fun pagerResource(loadState: CombinedLoadStates) : Flow<Resource<Unit>>
}