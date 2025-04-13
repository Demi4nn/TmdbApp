package com.example.tmdbapp.feature.home.home.data.mapper

import com.example.tmdpapp.core.common.model.data.now_playing.DatesResponse
import com.example.tmdpapp.core.common.model.data.now_playing.NowPlayingResponse
import com.example.tmdpapp.core.common.model.data.now_playing.ResultsResponse
import com.example.tmdpapp.core.common.model.domain.now_playing.Dates
import com.example.tmdpapp.core.common.model.domain.now_playing.NowPlaying
import com.example.tmdpapp.core.common.model.domain.now_playing.Results
import javax.inject.Inject

class NowPlayingMapper @Inject constructor() {

    fun map(response: NowPlayingResponse): NowPlaying = response.run {
        return NowPlaying(
            dates = dates.map(),
            page = page,
            results = results.map { it.map() },
            totalPages = totalPages,
            totalResults = totalResults
        )
    }

    private fun DatesResponse.map(): Dates {
        return Dates(
            maximum = maximum,
            minimum = minimum
        )
    }

    private fun ResultsResponse.map(): Results {
        return Results(
            adult = adult,
            backdropPath = backdropPath,
            genreIds = genreIds,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
    }
}