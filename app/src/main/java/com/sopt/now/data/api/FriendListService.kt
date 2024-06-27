package com.sopt.now.data.api

import com.sopt.now.data.dto.ResponseFriendListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendListService {
    @GET("api/users")
    suspend fun getFriendList(
        @Query("page") page: Int,
    ): ResponseFriendListDto
}