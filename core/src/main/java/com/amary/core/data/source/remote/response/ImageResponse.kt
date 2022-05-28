package com.amary.core.data.source.remote.response

import com.amary.core.domain.model.Image
import com.google.gson.annotations.SerializedName

data class ImageResponse(
    @SerializedName("backdrops") val backdrops: List<BackdropResponse>?,
) {
    fun mapToModel() = Image(
        backdrops?.map { it.mapToModel() }
    )
}
