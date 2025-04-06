package com.example.tmdbapp.data.mapper

import com.example.tmdbapp.data.response.DatesResponse
import com.example.tmdbapp.data.response.NowPlayingResponse
import com.example.tmdbapp.data.response.ResultsResponse
import com.example.tmdbapp.domain.entity.Dates
import com.example.tmdbapp.domain.entity.NowPlaying
import com.example.tmdbapp.domain.entity.Results
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