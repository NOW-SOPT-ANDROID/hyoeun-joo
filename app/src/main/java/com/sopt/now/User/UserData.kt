package com.sopt.now.User

import androidx.annotation.DrawableRes

data class UserData(
    @DrawableRes val profileImage: Int,
    val name: String,
    val selfDescription: String,

    )
