package com.sopt.now.compose.domain.model

data class AuthEntity(
    val id: String,
    val pw: String,
    val name: String? = null,
    val phone: String? = null
)
