package com.amary.simovies.domain.repository

import androidx.paging.PagingData
import com.amary.simovies.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun allMovie(): Flow<PagingData<Result>>
    fun allTv(): Flow<PagingData<Result>>
}