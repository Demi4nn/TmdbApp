package com.example.tmdbapp.core.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response


suspend fun <T: Any> responseHandler(
    execute: suspend () -> Response<T>
): Result<T> {
    return try {
        val response = withContext(Dispatchers.IO) {
            execute()
        }
        val body = response.body()
        if(response.isSuccessful && body != null) {
            Result.Success(body)
        } else {
            Result.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        Result.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        Result.Exception(e)
    }
}