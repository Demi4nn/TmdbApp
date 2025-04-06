package com.example.tmdbapp.data.model

import com.google.gson.annotations.SerializedName

data class CreateSessionRequest(
    @SerializedName("request_token")
    val requestToken: String
)