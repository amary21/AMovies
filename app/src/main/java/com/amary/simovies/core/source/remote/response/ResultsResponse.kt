package com.amary.simovies.core.source.remote.response

import com.amary.simovies.constant.EmptyValue
import com.amary.simovies.domain.model.Result
import com.google.gson.annotations.SerializedName

data class ResultsResponse(
	@SerializedName("overview") val overview: String = EmptyValue.STRING,
	@SerializedName("original_language") val originalLanguage: String = EmptyValue.STRING,
	@SerializedName("original_title") val originalTitle: String = EmptyValue.STRING,
	@SerializedName("video") val video: Boolean = EmptyValue.BOOLEAN,
	@SerializedName("title", alternate = ["name"]) val title: String = EmptyValue.STRING,
	@SerializedName("genre_ids") val genreIds: List<Int>,
	@SerializedName("poster_path") val posterPath: String = EmptyValue.STRING,
	@SerializedName("backdrop_path") val backdropPath: String = EmptyValue.STRING,
	@SerializedName("release_date", alternate = ["first_air_date"]) val releaseDate: String = EmptyValue.STRING,
	@SerializedName("popularity") val popularity: Double = EmptyValue.DOUBLE,
	@SerializedName("vote_average") val voteAverage: Double = EmptyValue.DOUBLE,
	@SerializedName("id") val id: Int = EmptyValue.INT,
	@SerializedName("adult") val adult: Boolean = EmptyValue.BOOLEAN,
	@SerializedName("vote_count") val voteCount: Int = EmptyValue.INT
) {
	fun mapToModel() = Result(
		overview, originalLanguage, originalTitle, video, title, genreIds, posterPath, backdropPath, releaseDate, popularity, voteAverage, id, adult, voteCount
	)
}