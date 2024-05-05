package com.sopt.now.api

import com.sopt.now.dto.LoginDto.RequestLogInDto
import com.sopt.now.dto.SignUp.RequestSignUpDto
import com.sopt.now.dto.LoginDto.ResponseLogInDto
import com.sopt.now.dto.SignUp.ResponseSignUpDto
import com.sopt.now.dto.ResponseUserProfile
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    @POST("member/login")
    fun logIn(
        @Body request: RequestLogInDto,
    ): Call<ResponseLogInDto>

    @GET("member/info")
    fun getUserInfo(
        @Header("memberId") memberId : Int
    ): Call<ResponseUserProfile>
}