package com.amary.core.data.source.local

import com.amary.core.data.source.local.entity.ResultEntity
import com.amary.core.data.source.local.room.Dao

class LocalSource(private val dao: Dao) {
    fun allFavoriteStories() = dao.allFavoriteResult()

    fun isFavorite(idResult: Int) = dao.isFavorite(idResult)

    suspend fun setFavorite(resultEntity: ResultEntity) = dao.setFavorite(resultEntity)

    suspend fun unSetFavorite(idResult: Int) = dao.unSetFavorite(idResult)
}