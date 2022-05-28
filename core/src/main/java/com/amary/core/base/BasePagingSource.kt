package com.amary.core.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.amary.core.data.source.remote.network.ApiResult
import com.amary.core.data.source.remote.response.ListResponse
import com.amary.core.data.source.remote.response.ResultsResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.io.IOException

open class BasePagingSource(
    val remoteSource: suspend (Int) -> Flow<ApiResult<ListResponse>>
) : PagingSource<Int, ResultsResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ResultsResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsResponse> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            when(val result = remoteSource(page).first()){
                is ApiResult.Success -> {
                    LoadResult.Page(
                        data = result.data.results,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = page + 1
                    )
                }
                is ApiResult.Error -> throw Exception(result.errorMessage)
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object{
        private const val STARTING_PAGE_INDEX = 1
        private const val DEFAULT_PAGE_SIZE = 30

        fun build(
            pageSize: Int = DEFAULT_PAGE_SIZE,
            enablePlaceholders: Boolean = false,
            remoteSource: suspend (Int) -> Flow<ApiResult<ListResponse>>
        ): Pager<Int, ResultsResponse> = Pager(
            config = PagingConfig(enablePlaceholders = enablePlaceholders, pageSize = pageSize),
            pagingSourceFactory = { BasePagingSource(remoteSource) }
        )
    }

}