package com.amary.simovies.di

import com.amary.simovies.BuildConfig
import com.amary.simovies.constant.KeyValue
import com.amary.simovies.core.Repository
import com.amary.simovies.core.source.remote.RemoteSource
import com.amary.simovies.core.source.remote.network.ApiService
import com.amary.simovies.domain.repository.IRepository
import com.amary.simovies.domain.usecase.Interact
import com.amary.simovies.domain.usecase.UseCase
import com.amary.simovies.presentation.ui.bookmark.BookmarkViewModel
import com.amary.simovies.presentation.ui.content.ContentViewModel
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val chuckedInterceptor =  ChuckerInterceptor.Builder(androidContext())
            .collector(ChuckerCollector(androidContext()))
            .maxContentLength(KeyValue.CONTENT_LENGTH)
            .alwaysReadResponseBody(true)
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(chuckedInterceptor)
            .connectTimeout(KeyValue.TIME_NETWORK, TimeUnit.SECONDS)
            .readTimeout(KeyValue.TIME_NETWORK, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    factory { Dispatchers.IO }
    single { RemoteSource(get(), get()) }
    single<IRepository> { Repository(get()) }
}

val useCaseModule = module {
    factory<UseCase> { Interact(get()) }
}

val viewModelModule = module {
    viewModel { ContentViewModel(get()) }
    viewModel { BookmarkViewModel(get()) }
}