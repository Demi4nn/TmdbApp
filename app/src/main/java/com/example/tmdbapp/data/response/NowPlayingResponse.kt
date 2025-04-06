package com.example.tmdbapp.data.response

import com.google.gson.annotations.SerializedName

data class NowPlayingResponse(
    @SerializedName("dates")
    val dates: DatesResponse,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ResultsResponse>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

