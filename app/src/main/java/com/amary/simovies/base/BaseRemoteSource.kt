package com.amary.simovies.base

import com.amary.simovies.core.source.remote.network.ApiResult
import com.amary.simovies.core.source.remote.response.ErrorAuthResponse
import com.amary.simovies.core.source.remote.response.ErrorDataResponse
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
                when(responseCall.code()){
                    401 -> {
                        val responseError = Gson().fromJson(responseCall.errorBody()?.charStream(), ErrorAuthResponse::class.java)
                        emit(ApiResult.Error(responseCall.code(), responseError.statusMessage))
                    }
                     else -> {
                         val responseError = Gson().fromJson(responseCall.errorBody()?.charStream(), ErrorDataResponse::class.java)
                         emit(ApiResult.Error(responseCall.code(), responseError.errors[0]))
                     }
                }
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