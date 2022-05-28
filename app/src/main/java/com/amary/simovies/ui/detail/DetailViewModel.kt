package com.amary.simovies.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.amary.core.constant.EmptyValue
import com.amary.core.domain.model.Detail
import com.amary.core.domain.model.ResultData
import com.amary.core.domain.usecase.UseCase
import java.text.SimpleDateFormat
import java.util.*

class DetailViewModel(private val useCase: UseCase) : ViewModel() {
    fun content(idContent: Int, idResult: Int) = useCase.contentDetail(idContent, idResult).asLiveData()

    fun imageBackdrop(idContent: Int, idResult: Int) = useCase.imageBackdrop(idContent, idResult).asLiveData()

    fun isFavorite(idResult: Int) = useCase.isFavorite(idResult).asLiveData()

    fun setFavorite(resultData: ResultData, idContent: Int) = useCase.setFavorite(resultData, idContent).asLiveData()

    fun getRuntime(data: Detail?, idContent: Int): String =
        if (idContent == 1) data?.runtime.toString() else data?.lastEpisodeToAir?.runtime.toString()

    fun convertDateFormat(date: String): String {
        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val newDate = parser.parse(date)
        val dateFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())
        return newDate?.let { dateFormat.format(it) } ?: EmptyValue.STRING
    }
}