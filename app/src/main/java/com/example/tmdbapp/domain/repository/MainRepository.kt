package com.example.tmdbapp.domain.repository

import com.example.tmdbapp.domain.entity.NowPlaying
import com.example.tmdbapp.core.network.Result

interface MainRepository {
    suspend fun getData(): Result<NowPlaying>
}