package com.example.tmdpapp.core.common.model.data.auth

import com.google.gson.annotations.SerializedName


data class CreateSessionResponse(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("session_id")
    val sessionId: String
)