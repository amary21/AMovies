package com.amary.core.domain.model

import com.amary.core.constant.EmptyValue

data class Detail(
    val runtime: Int? = EmptyValue.INT,
    val lastEpisodeToAir: LastEpisodeToAir?,
    val genres: List<Genre>
)