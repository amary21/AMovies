package com.amary.simovies.bookmark.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amary.core.domain.usecase.UseCase

class BookmarkViewModel(useCase: UseCase) : ViewModel() {
    val allFavorite = useCase.allFavorite().asLiveData()
}