package com.amary.simovies.core.source.remote.response

import com.amary.simovies.domain.model.Image
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("backdrops") val backdrops: List<BackdropResponse>?,
) {
    fun mapToModel() = Image(
        backdrops?.map { it.mapToModel() }
    )
}
