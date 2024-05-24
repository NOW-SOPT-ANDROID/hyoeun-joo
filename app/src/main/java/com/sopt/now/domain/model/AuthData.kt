package com.sopt.now.domain.model

import android.provider.ContactsContract.CommonDataKinds.Phone

data class AuthData(
    val id: String,
    val pw: String,
    val name: String? = null,
    val phone: String? = null
)
