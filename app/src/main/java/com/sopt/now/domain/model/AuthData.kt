package com.sopt.now.domain.model

import android.provider.ContactsContract.CommonDataKinds.Phone

data class AuthEntity(
    val id: String,
    val pw: String,
    val name: String? = null,
    val phone: String? = null
)
