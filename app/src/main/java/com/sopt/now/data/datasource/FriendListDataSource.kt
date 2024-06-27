package com.sopt.now.data.datasource

import com.sopt.now.data.dto.ResponseFriendListDto

interface FriendListDataSource {
    suspend fun getFriendList(page: Int): ResponseFriendListDto
}