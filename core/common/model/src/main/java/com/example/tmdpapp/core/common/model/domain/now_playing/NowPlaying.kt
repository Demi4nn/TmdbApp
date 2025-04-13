package com.example.tmdpapp.core.common.model.domain.now_playing

data class NowPlaying(
    val dates: Dates,
    val page: Int,
    val results: List<Results>,
    val totalPages: Int,
    val totalResults: Int
)