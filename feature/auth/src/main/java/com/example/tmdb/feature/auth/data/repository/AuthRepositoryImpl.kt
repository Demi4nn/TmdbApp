package com.example.tmdb.feature.auth.data.repository

import com.example.tmdb.feature.auth.data.api.AuthenticateService
import com.example.tmdb.feature.auth.domain.repository.AuthRepository
import com.example.tmdpapp.core.common.model.data.auth.CreateSessionRequest
import com.example.tmdpapp.core.common.model.data.auth.CreateSessionResponse
import com.example.tmdpapp.core.common.model.data.auth.RequestTokenResponse
import com.example.tmdpapp.core.common.model.data.auth.ValidateLoginRequest
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthenticateService,
    // apiKey можно передавать через DI (например, из BuildConfig)
    private val apiKey: String
) : AuthRepository {

    override suspend fun login(username: String, password: String): CreateSessionResponse {
        // 1. Получаем request token
        val tokenResponse: RequestTokenResponse = api.getRequestToken(apiKey)
        // 2. Валидируем token с использованием логина и пароля
        val validatedTokenResponse = api.validateRequestToken(
            apiKey,
            ValidateLoginRequest(
                username,
                password,
                tokenResponse.requestToken
            )
        )
        // 3. Создаем сессию на основе валидированного токена
        return api.createSession(apiKey,
            CreateSessionRequest(
                validatedTokenResponse.requestToken
            )
        )
    }
}