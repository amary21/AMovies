package com.amary.simovies.core.source.remote.response

import com.google.gson.annotations.SerializedName

data class ErrorDataResponse(
	@SerializedName("errors") val errors: List<String>
)