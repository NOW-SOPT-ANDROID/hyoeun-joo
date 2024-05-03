package com.sopt.now.User

import android.os.Parcelable
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.security.identity.AccessControlProfileId
import kotlinx.parcelize.Parcelize
import java.net.Authenticator
import java.sql.Struct


@Parcelize
data class UserDataInput(
    val userId: String,
    val userPW: String,
    val userNickName: String,
    val userMbti: String,
) : Parcelable {

}