package com.amary.core.di

import androidx.room.Room
import com.amary.core.BuildConfig
import com.amary.core.constant.KeyValue
import com.amary.core.data.Repository
import com.amary.core.data.source.local.LocalSource
import com.amary.core.data.source.local.room.DataBase
import com.amary.core.data.source.remote.RemoteSource
import com.amary.core.data.source.remote.network.ApiService
import com.amary.core.domain.repository.IRepository
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
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

val databaseModule = module {
    factory { get<DataBase>().dao() }
    single {
        Room.databaseBuilder(androidContext(), DataBase::class.java, KeyValue.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}

val repositoryModule = module {
    factory { Dispatchers.IO }
    single { RemoteSource(get(), get()) }
    single { LocalSource(get()) }
    single<IRepository> { Repository(get(), get()) }
}