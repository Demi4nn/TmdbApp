package com.example.tmdbapp.data.repository

import com.example.tmdbapp.data.api.AuthenticateService
import com.example.tmdbapp.data.model.CreateSessionRequest
import com.example.tmdbapp.data.model.CreateSessionResponse
import com.example.tmdbapp.data.model.RequestTokenResponse
import com.example.tmdbapp.data.model.ValidateLoginRequest
import com.example.tmdbapp.domain.repository.AuthRepository
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
            ValidateLoginRequest(username, password, tokenResponse.requestToken)
        )
        // 3. Создаем сессию на основе валидированного токена
        return api.createSession(apiKey, CreateSessionRequest(validatedTokenResponse.requestToken))
    }
}