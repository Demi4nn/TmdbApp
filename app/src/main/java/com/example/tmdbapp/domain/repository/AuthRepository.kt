package com.example.tmdbapp.domain.repository

import com.example.tmdbapp.data.model.CreateSessionResponse

interface AuthRepository {
    suspend fun login(username: String, password: String): CreateSessionResponse
}
