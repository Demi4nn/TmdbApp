package com.example.tmdbapp.data.repository

import com.example.tmdbapp.core.network.responseHandler
import com.example.tmdbapp.data.mapper.NowPlayingMapper
import com.example.tmdbapp.data.api.NowPlayingService
import com.example.tmdbapp.domain.repository.MainRepository
import com.example.tmdbapp.domain.entity.NowPlaying
import com.example.tmdbapp.core.network.Result
import com.example.tmdbapp.data.response.NowPlayingResponse
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: NowPlayingService,
    private val mapper: NowPlayingMapper
): MainRepository {

    override suspend fun getData(): Result<NowPlaying> {
        val result  = responseHandler {
            apiService.getData()
        }

        return when (result) {
            is Result.Success<NowPlayingResponse> -> {
                Result.Success(mapper.map(result.data))
            }
            else -> Result.Error(code = 500, message = "")
        }
    }
}