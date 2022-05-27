package com.amary.simovies.domain.model

import com.amary.simovies.constant.EmptyValue

data class Detail(
    val runtime: Int? = EmptyValue.INT,
    val lastEpisodeToAir: LastEpisodeToAir?,
    val genres: List<Genre>
)