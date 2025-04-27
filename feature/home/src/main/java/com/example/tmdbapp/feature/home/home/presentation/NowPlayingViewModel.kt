package com.example.tmdbapp.feature.home.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.feature.home.home.domain.repository.NowPlayingRepository
import com.example.tmdpapp.core.common.model.domain.now_playing.Results
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
class NowPlayingViewModel @Inject constructor(
    private val repository: NowPlayingRepository
): ViewModel() {

    private val state = MutableStateFlow(initState())
    private val events = MutableSharedFlow<Event>()

    private var pageCounter = 1

    fun getState(): StateFlow<MainState> = state.asStateFlow()
    fun getEvents(): SharedFlow<Event> = events.asSharedFlow()

    private fun initState() = MainState(
        isLoading = false,
        isBottomLoading = false,
        items = emptyList()
    )

    init {
        getInitialPlayingMovies()
    }

    fun onAction(action: Action) {
        when (action) {
            is Action.ScrollToEndList -> {
                viewModelScope.launch {
                events.emit(Event.showError(message = "Грузись, пожалуйста"))
                }
            }
        }
    }

    private fun getInitialPlayingMovies() {
        viewModelScope.launch {
            state.update { it.copy(isLoading = true) }
            getNowPlayingMovies()
            state.update { it.copy(isLoading = false) }
        }
    }

    private suspend fun getNowPlayingMovies(page: Int = pageCounter) {
       // viewModelScope.launch {
                when (val result = repository.getNowPlayingMovies(page)) {
                    is com.example.tmdbapp.core.network.Result.Success -> {
                        state.update { it.copy(items = it.items + result.data.results) }
                    }
                    else -> events.emit(Event.showError("Помогите"))
                }

          //  }
        }

    fun getNewMovies() {
        viewModelScope.launch {
            state.update { it.copy(isBottomLoading = true) }
            getNowPlayingMovies(++pageCounter)
            state.update { it.copy(isBottomLoading = false) }
        }

    }


    data class MainState(
        val isLoading: Boolean,
        val isBottomLoading: Boolean,
        val items: List<Results>
    )

    sealed interface Event {
        data class showError(val message: String): Event
    }

    sealed interface Action {
        data object ScrollToEndList: Action
    }
}