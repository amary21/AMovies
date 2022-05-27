package com.amary.core.domain.model

import com.amary.core.constant.EmptyValue
import com.amary.core.data.source.local.entity.ResultEntity
import java.io.Serializable

data class ResultData(
    val overview: String = EmptyValue.STRING,
    val originalLanguage: String = EmptyValue.STRING,
    val originalTitle: String = EmptyValue.STRING,
    val video: Boolean = EmptyValue.BOOLEAN,
    val title: String = EmptyValue.STRING,
    val posterPath: String = EmptyValue.STRING,
    val backdropPath: String = EmptyValue.STRING,
    val releaseDate: String = EmptyValue.STRING,
    val popularity: Double = EmptyValue.DOUBLE,
    val voteAverage: Double = EmptyValue.DOUBLE,
    val id: Int = EmptyValue.INT,
    val adult: Boolean = EmptyValue.BOOLEAN,
    val voteCount: Int = EmptyValue.INT,
    val idContent: Int = EmptyValue.INT
): Serializable {
    fun mapToEntity(idContent: Int) = ResultEntity(
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
