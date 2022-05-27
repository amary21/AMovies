package com.amary.simovies.core.source.remote.response

import com.amary.simovies.constant.EmptyValue
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
	@SerializedName("status_message") val statusMessage: String = EmptyValue.STRING,
	@SerializedName("status_code") val statusCode: Int = EmptyValue.INT
)