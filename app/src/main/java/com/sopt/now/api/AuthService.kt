package com.sopt.now.api

import com.sopt.now.dto.RequestLogInDto
import com.sopt.now.dto.RequestSignUpDto
import com.sopt.now.dto.ResponseLogInDto
import com.sopt.now.dto.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    fun logIn(
        @Body request: RequestLogInDto,
    ): Call<ResponseLogInDto>
}