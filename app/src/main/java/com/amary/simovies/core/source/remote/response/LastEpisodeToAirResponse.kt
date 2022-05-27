package com.amary.simovies.core.source.remote.response

import com.amary.simovies.constant.EmptyValue
import com.amary.simovies.domain.model.LastEpisodeToAir
import com.google.gson.annotations.SerializedName

data class LastEpisodeToAirResponse(
    @SerializedName("runtime") val runtime: Int? = EmptyValue.INT,
) {
    fun mapToModel() =  LastEpisodeToAir(runtime ?: EmptyValue.INT)
}
