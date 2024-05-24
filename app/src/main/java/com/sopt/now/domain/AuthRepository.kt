package com.sopt.now.domain

import com.sopt.now.domain.model.AuthData
import retrofit2.Response

interface AuthRepository {
    suspend fun logIn(authData: AuthData): Result<Response<Unit>>
    suspend fun signUp(authData: AuthData): Result<Response<Unit>>
}