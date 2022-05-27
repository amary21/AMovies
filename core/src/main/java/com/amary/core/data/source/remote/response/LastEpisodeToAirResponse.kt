package com.amary.core.data.source.remote.response

import com.amary.core.constant.EmptyValue
import com.amary.core.domain.model.LastEpisodeToAir
import com.google.gson.annotations.SerializedName

data class LastEpisodeToAirResponse(
    @SerializedName("runtime") val runtime: Int? = EmptyValue.INT,
) {
    fun mapToModel() =  LastEpisodeToAir(runtime ?: EmptyValue.INT)
}
