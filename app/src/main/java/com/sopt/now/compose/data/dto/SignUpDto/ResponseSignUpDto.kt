package com.sopt.now.compose.data.dto.SignUpDto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseSignUpDto(
    @SerialName("code")
    val code: Int,
    @SerialName("message")
    val message: String,
)