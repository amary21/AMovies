package com.amary.core.data.source.remote.response

import com.amary.core.constant.EmptyValue
import com.amary.core.domain.model.Backdrop
import com.google.gson.annotations.SerializedName

data class BackdropResponse(
    @SerializedName("file_path") val filePath: String? = EmptyValue.STRING,
) {
    fun mapToModel() = Backdrop(filePath ?: EmptyValue.STRING)
}
