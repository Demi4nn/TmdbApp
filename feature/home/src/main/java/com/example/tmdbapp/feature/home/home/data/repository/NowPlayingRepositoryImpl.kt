package com.example.tmdbapp.feature.home.home.data.repository


import com.example.tmdbapp.feature.home.home.domain.repository.NowPlayingRepository
import com.example.tmdpapp.core.common.model.domain.now_playing.NowPlaying
import com.example.tmdpapp.core.common.model.data.now_playing.NowPlayingResponse
import com.example.tmdbapp.feature.home.home.data.api.NowPlayingService
import com.example.tmdbapp.feature.home.home.data.mapper.NowPlayingMapper
import com.example.tmdbapp.core.network.Result
import com.example.tmdbapp.core.network.responseHandler
import javax.inject.Inject

class NowPlayingRepositoryImpl @Inject constructor(
    private val apiService: NowPlayingService,
    private val mapper: NowPlayingMapper
): NowPlayingRepository {

    override suspend fun getNowPlayingMovies(page: Int): Result<NowPlaying> {
        val result  = responseHandler {
            apiService.getNowPlayingMovies(page)
        }

        return when (result) {
            is Result.Success<NowPlayingResponse> -> {
                Result.Success(mapper.map(result.data))
            }
            else -> Result.Error(code = 500, message = "")
        }
    }
}