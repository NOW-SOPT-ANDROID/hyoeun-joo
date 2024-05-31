package com.sopt.now.compose.data

import com.sopt.now.compose.data.api.AuthService
import com.sopt.now.compose.data.dto.LoginDto.RequestLogInDto
import com.sopt.now.compose.data.dto.SignUpDto.RequestSignUpDto
import com.sopt.now.compose.domain.AuthRepository
import com.sopt.now.compose.domain.model.AuthEntity
import retrofit2.Response

class AuthRepoImpl(
    private val authService: AuthService,
) : AuthRepository {
    override suspend fun logIn(authEntity: AuthEntity): Result<Response<Unit>> = runCatching {
        authService.logIn(
            request = RequestLogInDto(
                authenticationId = authEntity.id,
                password = authEntity.pw
            )
        )
    }

    override suspend fun signUp(authEntity: AuthEntity): Result<Response<Unit>> = runCatching {
        authService.signUp(
            request = RequestSignUpDto(
                authenticationId = authEntity.id,
                password = authEntity.pw,
                nickname = authEntity.name ?: "",
                phone = authEntity.phone ?: ""

            )
        )
    }
}