package com.sopt.now.compose

import androidx.compose.ui.graphics.vector.ImageVector
import com.sopt.now.compose.Const.Companion.HOME
import com.sopt.now.compose.Const.Companion.MYPAGE
import com.sopt.now.compose.Const.Companion.SEARCH

sealed class BottomNavItem(
    val title: Int, val icon: Int, val label: String
) {
    object Home : BottomNavItem(R.string.BottomNavi_home, R.drawable.ic_home_white_24,  HOME)
    object Search : BottomNavItem(R.string.BottomNavi_search, R.drawable.ic_search_white_24, SEARCH)
    object MyPage : BottomNavItem(R.string.BottomNavi_myPage, R.drawable.ic_mypage_white_24, MYPAGE)
}