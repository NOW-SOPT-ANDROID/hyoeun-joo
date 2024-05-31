package com.sopt.now.data.api

import com.sopt.now.data.dto.LoginDto.RequestLogInDto
import com.sopt.now.data.dto.SignUp.RequestSignUpDto
import com.sopt.now.data.dto.LoginDto.ResponseLogInDto
import com.sopt.now.data.dto.SignUp.ResponseSignUpDto
import com.sopt.now.data.dto.ResponseUserProfile
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): Response<Unit>

    @POST("member/login")
    suspend fun logIn(
        @Body request: RequestLogInDto,
    ): Response<Unit>

    @GET("member/info")
    fun getUserInfo(
        @Header("memberId") memberId : Int
    ): Call<ResponseUserProfile>
}