package com.example.tmdbapp.feature.home.home.data.api

import com.example.tmdpapp.core.common.model.data.now_playing.NowPlayingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NowPlayingService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page")
        page: Int = 1
    ): Response<NowPlayingResponse>
}