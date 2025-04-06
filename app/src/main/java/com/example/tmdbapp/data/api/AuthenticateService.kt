package com.example.tmdbapp.data.api

import com.example.tmdbapp.data.model.CreateSessionRequest
import com.example.tmdbapp.data.model.CreateSessionResponse
import com.example.tmdbapp.data.model.RequestTokenResponse
import com.example.tmdbapp.data.model.ValidateLoginRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthenticateService {

    @GET("authentication/token/new")
    suspend fun getRequestToken(@Query("api_key") apiKey: String): RequestTokenResponse

    @POST("authentication/token/validate_with_login")
    suspend fun validateRequestToken(
        @Query("api_key") apiKey: String,
        @Body request: ValidateLoginRequest
    ): RequestTokenResponse

    @POST("authentication/session/new")
    suspend fun createSession(
        @Query("api_key") apiKey: String,
        @Body request: CreateSessionRequest
    ): CreateSessionResponse
}