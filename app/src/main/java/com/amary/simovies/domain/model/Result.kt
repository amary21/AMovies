package com.amary.simovies.domain.model

import com.amary.simovies.constant.EmptyValue

data class Result(
    val overview: String = EmptyValue.STRING,
    val originalLanguage: String = EmptyValue.STRING,
    val originalTitle: String = EmptyValue.STRING,
    val video: Boolean = EmptyValue.BOOLEAN,
    val title: String = EmptyValue.STRING,
    val genreIds: List<Int>,
    val posterPath: String = EmptyValue.STRING,
    val backdropPath: String = EmptyValue.STRING,
    val releaseDate: String = EmptyValue.STRING,
    val popularity: Double = EmptyValue.DOUBLE,
    val voteAverage: Double = EmptyValue.DOUBLE,
    val id: Int = EmptyValue.INT,
    val adult: Boolean = EmptyValue.BOOLEAN,
    val voteCount: Int = EmptyValue.INT
)
