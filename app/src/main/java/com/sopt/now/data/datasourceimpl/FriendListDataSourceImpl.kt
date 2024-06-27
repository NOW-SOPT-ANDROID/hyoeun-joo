package com.sopt.now.data.datasourceimpl

import com.sopt.now.data.api.FriendListService
import com.sopt.now.data.datasource.FriendListDataSource
import com.sopt.now.data.dto.ResponseFriendListDto
import com.sopt.now.domain.model.FriendListEntity

class FriendListDataSourceImpl(private val friendListService: FriendListService) :
    FriendListDataSource {
    /*  override suspend fun getFriendList(page: Int): List<FriendListEntity> {
        return friendListService.getFriendList(page).toReqresEntity()
    }*/
    override suspend fun getFriendList(page: Int): ResponseFriendListDto {
         return friendListService.getFriendList(page)
    }
}
