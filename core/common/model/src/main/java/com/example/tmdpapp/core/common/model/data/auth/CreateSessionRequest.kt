package com.example.tmdpapp.core.common.model.data.auth

import com.google.gson.annotations.SerializedName

data class CreateSessionRequest(
    @SerializedName("request_token")
    val requestToken: String
)