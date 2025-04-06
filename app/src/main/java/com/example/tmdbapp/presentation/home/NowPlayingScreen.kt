package com.example.tmdbapp.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import cafe.adriel.voyager.core.screen.Screen
import com.example.tmdbapp.presentation.MainViewModel
import kotlinx.coroutines.flow.collect

class NowPlayingScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = hiltViewModel<MainViewModel>()
        val state by viewModel.getState().collectAsState()
        val context = LocalContext.current

        LaunchedEffect(key1 = context) {
            viewModel.getEvents().collect {
                when (it) {
                    is MainViewModel.Event.showError -> {
                        Toast.makeText(context, it.message,Toast.LENGTH_LONG).show()
                    }
                }

            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 8.dp, start = 8.dp)
            ) {
                items(state.items) {
                    Text(
                        style = TextStyle(
                            fontSize = TextUnit(16f, TextUnitType.Sp),
                        ),
                        text = it.title,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier.padding(16.dp),
                        style = TextStyle(
                            fontSize = TextUnit(14f, TextUnitType.Sp),
                        ),
                        text = it.overview,
                        color = Color.Black
                    )
                }
            }
        }
    }

}
