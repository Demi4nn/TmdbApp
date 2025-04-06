package com.example.tmdbapp.di

import com.example.tmdbapp.data.repository.MainRepositoryImpl
import com.example.tmdbapp.domain.repository.MainRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class NowPlayingRepositoryModule {
    @Binds
    abstract fun bindRepository(impl: MainRepositoryImpl): MainRepository
}