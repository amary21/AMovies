package com.amary.simovies.domain.usecase

import androidx.paging.CombinedLoadStates
import com.amary.simovies.domain.repository.IRepository

class Interact(private val iRepository: IRepository): UseCase {
    override fun allMovie() = iRepository.allMovie()
    override fun allTv() = iRepository.allTv()
    override fun pagerResource(loadState: CombinedLoadStates) = iRepository.pagerResource(loadState)
}