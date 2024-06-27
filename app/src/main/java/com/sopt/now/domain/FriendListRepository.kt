package com.sopt.now.domain

import com.sopt.now.data.dto.ResponseFriendListDto
import com.sopt.now.domain.model.FriendListEntity

//서버에서 데이터를 가져오는 것
interface FriendListRepository {
    suspend fun getFreindList(page:Int):Result<List<FriendListEntity>>
}