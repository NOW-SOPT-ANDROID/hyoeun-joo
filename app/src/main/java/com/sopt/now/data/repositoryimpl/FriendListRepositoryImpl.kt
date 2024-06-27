package com.sopt.now.data.repositoryimpl

import com.sopt.now.data.datasource.FriendListDataSource
import com.sopt.now.domain.FriendListRepository
import com.sopt.now.domain.model.FriendListEntity

//class FriendListServiceImpl(
//    private val friendListService: FriendListService,
//) : FriendListRepository {
//    override suspend fun getFreindList(page: Int): Result<List<FriendListEntity>> = runCatching {
//        friendListService.getFriendList(page).toReqresEntity()
//    }
//}

class FriendListRepositoryImpl(
    private val dataSource: FriendListDataSource,
) : FriendListRepository {
    override suspend fun getFreindList(page: Int): Result<List<FriendListEntity>> = runCatching {
        dataSource.getFriendList(page).toReqresEntity()
    }
}

