package com.amary.simovies.core.source.remote.response

import com.amary.simovies.constant.EmptyValue
import com.amary.simovies.domain.model.Genre
import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("name") val name: String? = EmptyValue.STRING,
) {
    fun mapToModel() = Genre(name ?: EmptyValue.STRING)
}
