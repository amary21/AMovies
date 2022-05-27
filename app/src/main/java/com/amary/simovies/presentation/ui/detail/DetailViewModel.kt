package com.amary.simovies.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amary.simovies.constant.EmptyValue
import com.amary.simovies.domain.model.Detail
import com.amary.simovies.domain.model.Result
import com.amary.simovies.domain.usecase.UseCase
import java.text.SimpleDateFormat
import java.util.*

class DetailViewModel(private val useCase: UseCase) : ViewModel() {
    fun content(idContent: Int, idResult: Int) = useCase.contentDetail(idContent, idResult).asLiveData()

    fun imageBackdrop(idContent: Int, idResult: Int) = useCase.imageBackdrop(idContent, idResult).asLiveData()

    fun isFavorite(idResult: Int) = useCase.isFavorite(idResult).asLiveData()

    fun setFavorite(result: Result, idContent: Int) = useCase.setFavorite(result, idContent).asLiveData()

    fun getRuntime(data: Detail?, idContent: Int): String =
        if (idContent == 1) data?.runtime.toString() else data?.lastEpisodeToAir?.runtime.toString()

    fun convertDateFormat(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val newDate = parser.parse(date)
        val dateFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        return newDate?.let { dateFormat.format(it) } ?: EmptyValue.STRING
    }
}