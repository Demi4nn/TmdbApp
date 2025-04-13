package com.example.tmdb.feature.auth.domain.repository

import com.example.tmdpapp.core.common.model.data.auth.CreateSessionResponse

interface AuthRepository {
    suspend fun login(username: String, password: String): CreateSessionResponse
}
