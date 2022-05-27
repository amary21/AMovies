package com.amary.simovies.core.source.remote.response

import com.amary.simovies.constant.EmptyValue
import com.amary.simovies.domain.model.Detail
import com.google.gson.annotations.SerializedName

data class DetailResponse(
	@SerializedName("runtime") val runtime: Int? = EmptyValue.INT,
	@SerializedName("last_episode_to_air") val lastEpisodeToAir: LastEpisodeToAirResponse?,
	@SerializedName("genres") val genres: List<GenreResponse>
) {
	fun mapToModel() = Detail(
		runtime ?: EmptyValue.INT,
		lastEpisodeToAir?.mapToModel(),
		genres.map { it.mapToModel() }
	)
}