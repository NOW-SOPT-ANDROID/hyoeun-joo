package com.sopt.now.data

import com.sopt.now.data.api.AuthService
import com.sopt.now.data.dto.LoginDto.RequestLogInDto
import com.sopt.now.data.dto.SignUp.RequestSignUpDto
import com.sopt.now.domain.AuthRepository
import com.sopt.now.domain.model.AuthEntity
import retrofit2.Response

class AuthRepoImpl(
    private val authService: AuthService,
) : AuthRepository {
    override suspend fun logIn(authData: AuthEntity): Result<Response<Unit>> = runCatching  {
        authService.logIn(
            request = RequestLogInDto(
                authenticationId = authData.id,
                password = authData.pw
            )
        )
    }
    override suspend fun signUp(authData: AuthEntity): Result<Response<Unit>> = runCatching {
        authService.signUp(
            RequestSignUpDto(
                authenticationId = authData.id,
                password = authData.pw,
                nickname = authData.name ?: "",
                phone = authData.phone ?: ""
            )
        )
    }
}