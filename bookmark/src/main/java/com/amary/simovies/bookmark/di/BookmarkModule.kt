package com.amary.simovies.bookmark.di

import com.amary.simovies.bookmark.ui.BookmarkViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarkModule = module {
    viewModel { BookmarkViewModel(get()) }
}