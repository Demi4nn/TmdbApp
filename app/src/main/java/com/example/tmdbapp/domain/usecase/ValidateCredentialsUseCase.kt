package com.example.tmdbapp.domain.usecase

import com.example.tmdbapp.domain.model.ValidationError
import com.example.tmdbapp.domain.model.ValidationResult
import javax.inject.Inject

class ValidateCredentialsUseCase @Inject constructor() {
    fun execute(login: String, password: String): ValidationResult {
        val errors = mutableListOf<ValidationError>()

        if (login.isBlank() || login.length < 4) {
            errors.add(ValidationError.LoginTooShort)
        }
        if (password.isBlank() || password.length < 4) {
            errors.add(ValidationError.PasswordTooShort)
        }
        return ValidationResult(errors)
    }
}