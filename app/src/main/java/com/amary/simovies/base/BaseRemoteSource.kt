package com.amary.simovies.base

import com.amary.simovies.core.source.remote.network.ApiResult
import com.amary.simovies.core.source.remote.response.ErrorResponse
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

abstract class BaseRemoteSource(private val dispatcher: CoroutineDispatcher) {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>
    ) : Flow<ApiResult<T>> = flow {
        try {
            val responseCall = call()
            if (responseCall.isSuccessful && responseCall.body() != null){
                responseCall.body()?.let { emit(ApiResult.Success(it)) }
            } else {
                val responseError = Gson().fromJson(responseCall.errorBody()?.charStream(), ErrorResponse::class.java)
                emit(ApiResult.Error(responseCall.code(), responseError.statusMessage))
            }
        } catch (t: Throwable){
            when (t) {
                is HttpException ->
                    emit(ApiResult.Error(t.code(), t.message.toString()))
                else ->
                    emit(ApiResult.Error(400, t.message.toString()))
            }
        }
    }.flowOn(dispatcher)
}