package com.amary.simovies.core.source.local

import com.amary.simovies.core.source.local.entity.ResultEntity
import com.amary.simovies.core.source.local.room.Dao

class LocalSource(private val dao: Dao) {
    fun allFavoriteStories() = dao.allFavoriteResult()

    fun isFavorite(idResult: Int) = dao.isFavorite(idResult)

    suspend fun setFavorite(resultEntity: ResultEntity) = dao.setFavorite(resultEntity)

    suspend fun unSetFavorite(idResult: Int) = dao.unSetFavorite(idResult)
}