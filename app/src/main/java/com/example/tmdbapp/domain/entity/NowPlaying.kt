package com.example.tmdbapp.domain.entity

data class NowPlaying(
    val dates: Dates,
    val page: Int,
    val results: List<Results>,
    val totalPages: Int,
    val totalResults: Int
)