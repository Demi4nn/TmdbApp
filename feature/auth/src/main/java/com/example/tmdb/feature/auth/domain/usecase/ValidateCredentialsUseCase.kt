package com.example.tmdb.feature.auth.domain.usecase

import com.example.tmdpapp.core.common.model.domain.auth.ValidationError
import com.example.tmdpapp.core.common.model.domain.auth.ValidationResult
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