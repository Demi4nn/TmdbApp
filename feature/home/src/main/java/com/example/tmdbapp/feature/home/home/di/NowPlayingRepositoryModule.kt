package com.example.tmdbapp.feature.home.home.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.example.tmdbapp.feature.home.home.data.repository.NowPlayingRepositoryImpl
import com.example.tmdbapp.feature.home.home.domain.repository.NowPlayingRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class NowPlayingRepositoryModule {
    @Binds
    abstract fun bindRepository(impl: NowPlayingRepositoryImpl): NowPlayingRepository
}