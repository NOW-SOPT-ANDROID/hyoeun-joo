package com.sopt.now.data.dto

import com.sopt.now.domain.model.FriendListEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFriendListDto(
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int,
    @SerialName("data")
    val data: List<FriendListData>,
    @SerialName("support")
    val support: Support,
) {
    @Serializable
    data class FriendListData(
        @SerialName("id")
        val id: Int,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val first_name: String,
        @SerialName("last_name")
        val last_name: String,
        @SerialName("avatar")
        val avatar: String,
    )

    @Serializable
    data class Support(
        @SerialName("url")
        val url: String,
        @SerialName("text")
        val text: String,
    )

    fun toReqresEntity(): List<FriendListEntity> = data.map {
        FriendListEntity(
            email = it.email,
            firstName = it.first_name,
            lastName = it.last_name,
            avatar = it.avatar
        )
    }

//    fun toReqresList(): List<FriendListEntity> {
//        return data.map {
//            FriendListEntity(
//                email = it.email,
//                firstName = it.first_name,
//                lastName = it.last_name,
//                avatar = it.avatar
//            )
//        }
//    }

}

