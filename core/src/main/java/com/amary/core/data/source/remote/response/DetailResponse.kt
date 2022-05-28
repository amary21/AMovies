package com.amary.core.data.source.remote.response

import com.amary.core.constant.EmptyValue
import com.amary.core.domain.model.Detail
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