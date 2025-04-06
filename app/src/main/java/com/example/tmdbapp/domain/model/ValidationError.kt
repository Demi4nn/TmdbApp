package com.example.tmdbapp.domain.model

sealed class ValidationError {
    object LoginTooShort : ValidationError()
    object PasswordTooShort : ValidationError()
}