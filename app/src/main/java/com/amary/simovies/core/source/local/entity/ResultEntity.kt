package com.amary.simovies.core.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amary.simovies.constant.EmptyValue
import com.amary.simovies.domain.model.Result

@Entity(tableName = "result")
data class ResultEntity(
    @ColumnInfo(name = "overview") val overview: String = EmptyValue.STRING,
    @ColumnInfo(name = "original_language") val originalLanguage: String = EmptyValue.STRING,
    @ColumnInfo(name = "original_title")val originalTitle: String = EmptyValue.STRING,
    @ColumnInfo(name = "video") val video: Boolean = EmptyValue.BOOLEAN,
    @ColumnInfo(name = "title") val title: String = EmptyValue.STRING,
    @ColumnInfo(name = "poster_path") val posterPath: String = EmptyValue.STRING,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String = EmptyValue.STRING,
    @ColumnInfo(name = "release_date") val releaseDate: String = EmptyValue.STRING,
    @ColumnInfo(name = "popularity") val popularity: Double = EmptyValue.DOUBLE,
    @ColumnInfo(name = "vote_average") val voteAverage: Double = EmptyValue.DOUBLE,
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id") val id: Int = EmptyValue.INT,
    @ColumnInfo(name = "adult") val adult: Boolean = EmptyValue.BOOLEAN,
    @ColumnInfo(name = "vote_count") val voteCount: Int = EmptyValue.INT,
    @ColumnInfo(name = "id_content") val idContent: Int = EmptyValue.INT
) {
    fun mapToModel() = Result(
        overview,
        originalLanguage,
        originalTitle,
        video,
        title,
        posterPath,
        backdropPath,
        releaseDate,
        popularity,
        voteAverage,
        id,
        adult,
        voteCount,
        idContent
    )
}