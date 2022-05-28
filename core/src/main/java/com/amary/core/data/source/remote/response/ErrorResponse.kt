package com.amary.core.data.source.remote.response

import com.amary.core.constant.EmptyValue
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
	@SerializedName("status_message") val statusMessage: String = EmptyValue.STRING,
	@SerializedName("status_code") val statusCode: Int = EmptyValue.INT
)