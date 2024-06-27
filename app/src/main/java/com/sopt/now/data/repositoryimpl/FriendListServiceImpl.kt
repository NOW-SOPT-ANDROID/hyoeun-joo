package com.sopt.now.data.repositoryimpl

import com.sopt.now.data.api.FriendListService
import com.sopt.now.domain.FriendListRepository
import com.sopt.now.domain.model.FriendListEntity

class FriendListServiceImpl(
    private val friendListService: FriendListService,
) : FriendListRepository {
    override suspend fun getFreindList(page: Int): Result<List<FriendListEntity>> = runCatching {
        friendListService.getFriendList(page).toReqresEntity()
    }
}


