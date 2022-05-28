package com.amary.core.data.source.remote.response

import com.amary.core.constant.EmptyValue
import com.google.gson.annotations.SerializedName

data class ListResponse(
	@SerializedName("page") val page: Int = EmptyValue.INT,
	@SerializedName("total_pages") val totalPages: Int = EmptyValue.INT,
	@SerializedName("results") val results: List<ResultsResponse>,
	@SerializedName("total_results") val totalResults: Int = EmptyValue.INT
)