package com.sopt.now.domain

import com.sopt.now.domain.model.AuthEntity
import retrofit2.Response

interface AuthRepository {
    suspend fun logIn(authData: AuthEntity): Result<Response<Unit>>
    suspend fun signUp(authData: AuthEntity): Result<Response<Unit>>
}