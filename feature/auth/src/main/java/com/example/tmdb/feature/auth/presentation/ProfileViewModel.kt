package com.example.tmdb.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdpapp.core.common.model.domain.auth.ValidationError
import com.example.tmdb.feature.auth.domain.usecase.ValidateCredentialsUseCase
import com.example.tmdb.feature.auth.domain.usecase.AuthenticateUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val validateCredentialsUseCase: ValidateCredentialsUseCase,
    private val authenticateUserUseCase: AuthenticateUserUseCase
): ViewModel() {
    private val state = MutableStateFlow(initState())
    private val events = MutableSharedFlow<Event>()

    fun getState(): StateFlow<ProfileState> = state.asStateFlow()
    fun getEvents(): SharedFlow<Event> = events.asSharedFlow()

    private fun initState() = ProfileState(
        isLoading = false,
        login = "",
        password = "",
        loginError = "",
        passwordError = "",
        isPasswordVisible = false //new
    )

    fun updateLogin(text: String) {
        //todo validation of login
        state.update {
            it.copy(login = text,loginError = "")
        }
    }

    fun updatePassword(text: String) {
        //todo validation of password
        state.update {
            it.copy(password = text, passwordError = "")
        }

    }

    fun togglePasswordVisibility() {
        state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
    }

    fun onLoginClick() {
        val currentState = state.value
        val validationResult = validateCredentialsUseCase.execute(
            currentState.login,
            currentState.password
        )

        if (validationResult.errors.isEmpty()) {
            viewModelScope.launch {
                try {
                    val sessionResponse = authenticateUserUseCase.execute(
                        currentState.login,
                        currentState.password
                    )
                    if (sessionResponse.success) {
                        events.emit(Event.ShowMessage("Вход выполнен. Session: ${sessionResponse.sessionId}"))
                    } else {
                        events.emit(Event.ShowMessage("Ошибка создания сессии"))
                    }
                } catch (e: Exception) {
                    events.emit(Event.ShowMessage("Ошибка авторизации: ${e.message}"))
                }
            }
        } else {
            state.update {
                it.copy(
                    loginError = if (validationResult.errors.any { error ->
                        error is ValidationError.LoginTooShort })
                        "Логин должен содержать не менее 4 символов" else "",
                    passwordError = if (validationResult.errors.any { error ->
                        error is ValidationError.PasswordTooShort })
                        "Пароль должен содержать не менее 4 символов" else ""
                )
            }

        }
    }

    data class ProfileState(
        val isLoading: Boolean,
        val login: String,
        val password: String,
        val loginError: String,
        val passwordError: String,
        val isPasswordVisible: Boolean //new
    )

    sealed interface Event {
        data class ShowMessage(val message: String) : Event
    }
}
