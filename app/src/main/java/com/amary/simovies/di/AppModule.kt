package com.amary.simovies.di

import com.amary.core.domain.usecase.Interact
import com.amary.core.domain.usecase.UseCase
import com.amary.simovies.ui.content.ContentViewModel
import com.amary.simovies.ui.detail.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<UseCase> { Interact(get()) }
}

val viewModelModule = module {
    viewModel { ContentViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}