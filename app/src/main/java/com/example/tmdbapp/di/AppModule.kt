package com.example.tmdbapp.di

import com.example.tmdbapp.feature.home.home.data.api.NowPlayingService
import com.example.tmdb.feature.auth.data.api.AuthenticateService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI0ODNkYzdlMzg3MmZiYThkOTk1ZWE5ZTEzNDdjNmExOCIsIm5iZiI6MTc0MzMyNDI4NC45ODQsInN1YiI6IjY3ZTkwNDdjM2ZjYTkwZDJkZmY2Y2Y1OSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.EPTIF_VIr-xXBUHCk3V7fChnvHy5GUdL6v0HPxUXZT4"

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .addInterceptor { chain ->
            val request = chain.request()
            chain.proceed(
                request.newBuilder().addHeader(
                    "Authorization", "Bearer $API_KEY"
                )
                    .build()
            )
        }
        .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): NowPlayingService =
        retrofit.create(NowPlayingService::class.java)

    @Singleton
    @Provides
    fun provideAuthenticateService(retrofit: Retrofit): AuthenticateService =
        retrofit.create(AuthenticateService::class.java)

    @Singleton
    @Provides
    fun provideAuthenticateApiKey(): String = API_KEY

}
