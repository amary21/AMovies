package com.amary.core.data.source.remote.response

import com.amary.core.constant.EmptyValue
import com.amary.core.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("name") val name: String? = EmptyValue.STRING,
) {
    fun mapToModel() = Genre(name ?: EmptyValue.STRING)
}
