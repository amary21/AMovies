package com.amary.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amary.core.data.source.local.entity.ResultEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM result")
    fun allFavoriteResult(): Flow<List<ResultEntity>>

    @Query("SELECT EXISTS (SELECT * FROM result WHERE id=:idResult)")
    fun isFavorite(idResult: Int): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavorite(result: ResultEntity)

    @Query("DELETE FROM result WHERE id=:idResult")
    suspend fun unSetFavorite(idResult: Int)
}