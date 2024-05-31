package com.sopt.now.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserProfile( //응답 구조
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
    @SerialName("data")
    val data: UserData,
) {
    @Serializable
    data class UserData( //JSON 응답의 data 구조
        @SerialName("authenticationId")
        val authenticationId: String,
        @SerialName("nickname")
        val nickname: String,
        @SerialName("phone")
        val phone: String,
    )
}