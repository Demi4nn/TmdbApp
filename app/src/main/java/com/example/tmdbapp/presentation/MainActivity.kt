package com.example.tmdbapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.example.tmdbapp.navigation.TabNavigationItem
import com.example.tmdbapp.feature.home.home.presentation.NowPlayingTab
import com.example.tmdb.feature.auth.presentation.ProfileTab
import com.example.tmdbapp.common.ui.TmdbAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            com.example.tmdbapp.common.ui.TmdbAppTheme {
                MainRoute()
            }
        }
    }
}

@Composable
fun MainRoute() {
    TabNavigator(NowPlayingTab) {
        Scaffold(
            content = { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    CurrentTab()
                }
            },
            bottomBar = {
                NavigationBar {
                    TabNavigationItem(NowPlayingTab)
                    TabNavigationItem(ProfileTab)
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    com.example.tmdbapp.common.ui.TmdbAppTheme {
        MainRoute()
    }
}