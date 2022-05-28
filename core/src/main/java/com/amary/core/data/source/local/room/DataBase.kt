package com.amary.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amary.core.data.source.local.entity.ResultEntity

@Database(entities = [ResultEntity::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {
    abstract fun dao(): Dao
}