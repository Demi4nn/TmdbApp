package com.example.tmdpapp.core.common.model.domain.auth

sealed class ValidationError {
    object LoginTooShort : ValidationError()
    object PasswordTooShort : ValidationError()
}