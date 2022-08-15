package com.amary.core.data.source.remote.network

import com.amary.core.constant.BaseKey
import com.amary.core.data.source.remote.response.DetailResponse
import com.amary.core.data.source.remote.response.ImageResponse
import com.amary.core.data.source.remote.response.ListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun allMoviePopular(
        @Query("api_key") apiKey: String = BaseKey.API_KEY,
        @Query("page") page: Int
    ): Response<ListResponse>

    @GET("tv/popular")
    suspend fun allTvPopular(
        @Query("api_key") apiKey: String = BaseKey.API_KEY,
        @Query("page") page: Int
    ): Response<ListResponse>

    @GET("{content}/{id_result}")
    suspend fun contentDetail(
        @Path("content") content: String,
        @Path("id_result") idResult: Int,
        @Query("api_key") apiKey: String = BaseKey.API_KEY,
    ): Response<DetailResponse>

    @GET("{content}/{id_result}/images")
    suspend fun imageBackdrop(
        @Path("content") content: String,
        @Path("id_result") idResult: Int,
        @Query("api_key") apiKey: String = BaseKey.API_KEY,
    ): Response<ImageResponse>
}