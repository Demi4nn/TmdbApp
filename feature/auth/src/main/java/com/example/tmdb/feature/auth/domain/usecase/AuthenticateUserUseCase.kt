package com.example.tmdb.feature.auth.domain.usecase

import com.example.tmdb.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute(username: String, password: String) =
        authRepository.login(username, password)
}