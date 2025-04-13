package com.example.tmdpapp.core.common.model.data.auth

import com.google.gson.annotations.SerializedName

data class ValidateLoginRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("request_token")
    val requestToken: String
)