package com.amary.simovies.core.source.remote.response

import com.amary.simovies.constant.EmptyValue
import com.amary.simovies.domain.model.Backdrop
import com.google.gson.annotations.SerializedName

data class BackdropResponse(
    @SerializedName("file_path") val filePath: String? = EmptyValue.STRING,
) {
    fun mapToModel() = Backdrop(filePath ?: EmptyValue.STRING)
}
