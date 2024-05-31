package com.sopt.now.compose.feature.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDataInput(
    val userId: String,
    val userPW: String,
    val userNickName: String,
    val userMbti: String,
) : Parcelable