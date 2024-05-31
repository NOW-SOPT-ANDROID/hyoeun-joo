package com.sopt.now.presentation.User

import androidx.annotation.DrawableRes

data class UserData(
    @DrawableRes val profileImage: Int,
    val name: String,
    val selfDescription: String,

    )
