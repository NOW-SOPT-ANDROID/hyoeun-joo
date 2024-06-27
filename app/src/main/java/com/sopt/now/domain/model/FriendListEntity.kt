package com.sopt.now.domain.model

//서버에서 받아온 데이터를 이 데이터 클래스로 매핑
data class FriendListEntity(
    val email: String,
    val firstName: String,
    val lastName: String,
    val avatar: String
)