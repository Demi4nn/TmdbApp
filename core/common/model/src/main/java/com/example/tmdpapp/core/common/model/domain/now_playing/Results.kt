package com.example.tmdpapp.core.common.model.domain.now_playing

data class Results(
    val adult: Boolean,
    val backdropPath: String?, // !!!!!!!!!
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
