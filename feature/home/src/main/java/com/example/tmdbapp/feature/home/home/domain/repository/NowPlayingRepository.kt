package com.example.tmdbapp.feature.home.home.domain.repository

import com.example.tmdpapp.core.common.model.domain.now_playing.NowPlaying
import com.example.tmdbapp.core.network.Result

interface NowPlayingRepository {
    suspend fun getNowPlayingMovies(page: Int): Result<NowPlaying>
}