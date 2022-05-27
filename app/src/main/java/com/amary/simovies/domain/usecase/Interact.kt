package com.amary.simovies.domain.usecase

import androidx.paging.CombinedLoadStates
import com.amary.simovies.domain.model.Result
import com.amary.simovies.domain.repository.IRepository

class Interact(private val iRepository: IRepository): UseCase {
    override fun allMovie() = iRepository.allMovie()
    override fun allTv() = iRepository.allTv()
    override fun pagerResource(loadState: CombinedLoadStates) = iRepository.pagerResource(loadState)
    override fun contentDetail(idContent: Int, idResult: Int) = iRepository.contentDetail(idContent, idResult)
    override fun imageBackdrop(idContent: Int, idResult: Int) = iRepository.imageBackdrop(idContent, idResult)
    override fun allFavorite() = iRepository.allFavorite()
    override fun isFavorite(idResult: Int) = iRepository.isFavorite(idResult)
    override fun setFavorite(result: Result, idContent: Int) = iRepository.setFavorite(result, idContent)
}