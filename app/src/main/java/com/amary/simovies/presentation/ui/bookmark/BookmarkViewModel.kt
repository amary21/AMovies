package com.amary.simovies.presentation.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amary.simovies.domain.usecase.UseCase

class BookmarkViewModel(useCase: UseCase) : ViewModel() {
    val allFavorite = useCase.allFavorite().asLiveData()
}