package com.example.tmdbapp.domain.usecase

import com.example.tmdbapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute(username: String, password: String) =
        authRepository.login(username, password)
}