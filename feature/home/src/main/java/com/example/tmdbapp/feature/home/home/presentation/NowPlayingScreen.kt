package com.example.tmdbapp.feature.home.home.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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

class NowPlayingScreen: Screen {
    @Composable
    override fun Content() {
        val viewModel = hiltViewModel<NowPlayingViewModel>()
        val state by viewModel.getState().collectAsState()
        val context = LocalContext.current
        val scrollState = rememberLazyListState()
        val firstItemVisible by remember {
            derivedStateOf {
                scrollState.firstVisibleItemIndex > state.items.size - 7
            }
        }

        LaunchedEffect(firstItemVisible) {
            if(firstItemVisible) {
                viewModel.onAction(NowPlayingViewModel.Action.ScrollToEndList)
                viewModel.getNewMovies()
            }
        }

        LaunchedEffect(key1 = context) {
            viewModel.getEvents().collect {
                when (it) {
                    is NowPlayingViewModel.Event.showError -> {
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
                contentPadding = PaddingValues(top = 8.dp, start = 8.dp),
                state = scrollState
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
                if(state.isBottomLoading) {
                    item {
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp)
                            .height(60.dp),
                            contentAlignment = Alignment.Center
                        )
                        //Box(modifier = Modifier.fillMaxSize().align(Alignment.BottomCenter))
                        {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }

}
