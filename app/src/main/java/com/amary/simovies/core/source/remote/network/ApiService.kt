package com.amary.simovies.core.source.remote.network

import com.amary.simovies.BuildConfig
import com.amary.simovies.core.source.remote.response.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun allMoviePopular(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int
    ): Response<ApiResponse>

    @GET("tv/popular")
    fun allTvPopular(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int
    ): Response<ApiResponse>
}