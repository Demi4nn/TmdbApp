package com.example.tmdbapp.feature.home.home.data.api

import com.example.tmdpapp.core.common.model.data.now_playing.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET

interface NowPlayingService {
    @GET("movie/now_playing")
    suspend fun getData(): Response<NowPlayingResponse>
}