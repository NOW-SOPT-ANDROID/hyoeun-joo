package com.sopt.now.Friend

import androidx.annotation.DrawableRes

data class FriendData(
    @DrawableRes val profileImage: Int,
    val name: String,
    val selfDescription: String,

    )
